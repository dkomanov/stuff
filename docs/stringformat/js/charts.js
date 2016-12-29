function loadScalaSerializationChartsPage(rawList) {
  // ordered according to view
  var DataSizeToView = [
    {
      name: "tiny (7)",
      value: "Tiny"
    },
    {
      name: "very short (17)",
      value: "VeryShort"
    },
    {
      name: "short (29)",
      value: "Short"
    },
    {
      name: "medium (75)",
      value: "Medium"
    },
    {
      name: "long (212)",
      value: "Long"
    },
    {
      name: "very long (1004)",
      value: "VeryLong"
    },
    {
      name: "very long size miss (1006)",
      value: "VeryLongSizeMiss"
    }
  ];
  var Methods = [
    {
      name: 'javaConcat',
      color: '#f4cccc'
    },
    {
      name: 'stringFormat',
      color: '#f49999'
    },
    {
      name: 'messageFormat',
      color: '#ff0000'
    },
    {
      name: 'scalaConcat',
      color: '#cc0000'
    },
    {
      name: 'concatOptimized1',
      color: '#e69138'
    },
    {
      name: 'concatOptimized2',
      color: '#ff9900'
    },
    {
      name: 'concatOptimizedMacros',
      color: '#999900'
    },
    {
      name: 'slf4j',
      color: '#6aa84f'
    },
    {
      name: 'sInterpolator',
      color: '#0000ff'
    },
    {
      name: 'fInterpolator',
      color: '#cccccc'
    },
    {
      name: 'rawInterpolator',
      color: '#999999'
    },
    {
      name: 'sfiInterpolator',
      color: '#000000'
    }
  ];

  var data = convertData(rawList);

  $('.ssf-value-btn').click(function () {
    var property = $(this).attr('data-property');

    buildChartAndRawData({
      elem: '.big-chart',
      title: 'String formatting',
      subtitle: 'times, nanos',
      property: property
    });

    $('.ssf-value-btn').removeClass('active');
    $(this).addClass('active');
  });

  $('.ssf-value-btn[data-property=avg]').click();

  function buildChartAndRawData(opts) {
    buildChart(opts.elem, opts.title, opts.subtitle, getDataForChart(opts.property));
    buildRawData(opts.elem + '-raw-data', opts.property)
  }

  function buildChart(elem, title, subtitle, dataArray) {
    var chartData = google.visualization.arrayToDataTable(dataArray);

    var series = {};
    $.each(Methods, function (index, method) {
      series[index] = {
        color: method.color
      }
    });

    var options = {
      chart: {
        title: title,
        subtitle: subtitle
      },
      series: series
    };

    var chart = new google.charts.Bar($(elem).get(0));

    chart.draw(chartData, google.charts.Bar.convertOptions(options));
  }

  function buildRawData(elem, property) {
    var table = $('<table>')
      .addClass('table table-striped table-condensed');

    var head = $('<tr>');
    head.append($('<th>').html('Method \\ String Length'));
    $.each(DataSizeToView, function (index, dataSize) {
      head.append($('<th>').html(dataSize.name));
    });
    table.append($('<thead>').append(head));

    var tbody = $('<tbody>');
    $.each(data, function (index, value) {
      var tr = $('<tr>');

      function addTd(text) {
        tr.append($('<td>').html(text));
      }

      addTd(value.name);

      $.each(DataSizeToView, function (index, dataSize) {
        var time = getTime(value, dataSize.value, property);
        addTd(time);
      });

      tbody.append(tr);
    });
    table.append(tbody);

    $(elem).empty().append(table);
  }

  function getOrUpdate(map, name, defaultValue) {
    var v = map[name] || defaultValue || {};
    map[name] = v;
    return v;
  }

  function convertData(list) {
    // {
    //   name: javaConcat,
    //   values: {
    //     7: {avg, p0...},
    //     101: {avg, p0...}
    //   }
    // }
    //
    var map = {};

    $.each(list, function (index, entry) {
      // code from Benchmarks!
      var type = entry.params
        ? entry.params.arg
        : '';

      var name = extractName(entry.benchmark);
      var pm = entry.primaryMetric;

      var timesByStringLength = getOrUpdate(map, name, {name: name, values: {}}).values;
      timesByStringLength[type] = {
        avg: pm.score,
        p0: pm.scorePercentiles['0.0'],
        p50: pm.scorePercentiles['50.0'],
        p95: pm.scorePercentiles['95.0'],
        p100: pm.scorePercentiles['100.0']
      };
    });

    var result = [];
    $.each(Methods, function (index, method) {
      result.push(map[method.name] || {name: method.name});
    });

    return result;
  }

  function extractName(benchmark) {
    //'com.komanov.stringformat.jmh.ManyParamsBenchmark.concat'

    var index = benchmark.lastIndexOf('.');
    if (index == -1) {
      throw new Error('Expected a dot in a benchmark: ' + benchmark);
    }
    return benchmark.substring(index + 1);
  }

  function getDataForChart(property) {
    var result = [];

    var header = ['string length'];
    $.each(data, function (index, value) {
      header.push(value.name);
    });
    result.push(header);

    $.each(DataSizeToView, function (index, dataSize) {
      var line = [dataSize.name];
      $.each(data, function (index, value) {
        var time = getTime(value, dataSize.value, property);
        line.push(time);
      });
      result.push(line);
    });

    return result;
  }

  function getTime(value, dataSize, property) {
    return Math.floor(((value.values || {})[dataSize] || {})[property] || 0.0);
  }
}

$(document).ready(function () {
  loadGoogleCharts(loadJmhResult);
});

function loadGoogleCharts(onLoadCallback) {
  google.charts.load('current', {'packages': ['bar']});
  google.charts.setOnLoadCallback(onLoadCallback);
}

function loadJmhResult() {
  var url = 'jmh-result.json';
  $.getJSON(url, function (list) {
    loadScalaSerializationChartsPage(list);
  });
}
