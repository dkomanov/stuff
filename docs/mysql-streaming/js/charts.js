function loadMysqlStreamingChartsPage(rawState) {
  // ordered according to view
  var Limits = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000];
  var Libraries = [
    {
      driver: 'ConnectorJ',
      method: 'atOnce',
      color: '#990000'
    },
    {
      driver: 'ConnectorJ',
      method: 'stream',
      color: '#ff0000'
    },
    {
      driver: 'MariaDb',
      method: 'atOnce',
      color: '#000099'
    },
    {
      driver: 'MariaDb',
      method: 'stream',
      color: '#0000ff'
    }
  ];

  var state = {
    local: convertData(rawState.local),
    wifi: convertData(rawState.wifi),
    wire: convertData(rawState.wire),
    strange: convertData(rawState.strange)
  };
  var configuration = 'wire';
  var property = 'p95';

  initializeButtonGroup($('.ssa-configuration-btn'), 'data-configuration', function (v) {
    configuration = v;
  });

  initializeButtonGroup($('.ssa-value-btn'), 'data-property', function (v) {
    property = v;
  });

  // apply default values and initialize buttons
  $('.ssa-configuration-btn[data-configuration=wire]').click();
  $('.ssa-value-btn[data-property=p95]').click();

  function initializeButtonGroup(el, dataAttr, setter) {
    el.click(function () {
      setter($(this).attr(dataAttr));

      refreshCharts();

      el.removeClass('active');
      $(this).addClass('active');
    });
  }

  function refreshCharts() {
    buildChartAndRawData({
      elem: '.below-100',
      title: 'LIMIT below 100, microseconds',
      limits: {min: 0, max: 100}
    });

    buildChartAndRawData({
      elem: '.all-drivers',
      title: 'All LIMITs, microseconds',
      limits: {min: 0, max: 1000}
    });
  }

  function buildChartAndRawData(opts) {
    buildChart(opts.elem, opts.title, opts.limits, getDataForChart(opts.limits));
    buildRawData(opts.elem + '-raw-data');
  }

  function getTicks(inc, max) {
    var result = [];
    for (var i = 0; i <= max; i += inc) {
      result.push(i);
    }
    return result;
  }

  function buildChart(elem, title, limits, dataArray) {
    var chartData = google.visualization.arrayToDataTable(dataArray);

    var series = {};
    $.each(Libraries, function (index, lib) {
      series[index] = {
        color: lib.color
      }
    });

    var options = {
      title: title,
      //curveType: 'function',
      legend: {
        position: 'bottom'
      },
      focusTarget: 'category',
      hAxis: {
        title: 'row count',
        format: 'decimal',
        minValue: 0,
        maxValue: limits.max,
        ticks: limits.max <= 100 ? getTicks(10, limits.max) : getTicks(100, limits.max)
      },
      vAxis: {
        title: 'time, microseconds'
      },
      series: series
    };

    var chart = new google.visualization.LineChart($(elem).get(0));

    chart.draw(chartData, options);
  }

  function buildRawData(elem) {
    var table = $('<table>')
      .addClass('table table-striped table-condensed');

    var head = $('<tr>');
    head.append($('<th>').html('Library \\ Data Size'));
    $.each(Limits, function (index, limit) {
      head.append($('<th>').html(limit));
    });
    table.append($('<thead>').append(head));

    var tbody = $('<tbody>');
    $.each(state[configuration], function (index, value) {
      var tr = $('<tr>');

      function addTd(text) {
        tr.append($('<td>').html(text));
      }

      addTd(value.driver + '.' + value.method);

      $.each(Limits, function (index, limit) {
        addTd(getTime(value, limit, property));
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

  function convertData(lists) {
    function getKey(t) {
      return t.driver + '.' + t.method;
    }

    var map = {};

    $.each(lists, function (index, list) {
      $.each(list, function (index, entry) {
        var t = extractFromBenchmark(entry.benchmark, entry.params.limit);
        var pm = entry.primaryMetric;

        var timeByLimit = getOrUpdate(
          map,
          getKey(t),
          {
            driver: t.driver,
            method: t.method,
            values: {}
          }
        ).values;
        timeByLimit[t.limit] = {
          avg: pm.score,
          p0: pm.scorePercentiles['0.0'],
          p50: pm.scorePercentiles['50.0'],
          p95: pm.scorePercentiles['95.0'],
          p100: pm.scorePercentiles['100.0']
        };
      });
    });

    var result = [];
    $.each(Libraries, function (index, lib) {
      result.push(map[getKey(lib)]);
    });

    return result;
  }

  function extractFromBenchmark(benchmark, limit) {
    //'com.komanov.mysql.streaming.jmh.ConnectorJBenchmark.atOnce'

    var benchmarkParts = benchmark.split('Benchmark.');
    if (benchmarkParts.length != 2) {
      throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
    }

    return {
      driver: benchmarkParts[0].substring(benchmarkParts[0].lastIndexOf('.') + 1),
      method: benchmarkParts[1],
      limit: limit
    };
  }

  function getDataForChart(limits) {
    var data = state[configuration];
    var result = [];

    var header = ['limit'];
    $.each(data, function (index, value) {
      header.push(value.driver + '.' + value.method);
    });
    result.push(header);

    $.each(Limits, function (index, limit) {
      if (limits.min <= limit && limit <= limits.max) {
        var line = [limit];
        $.each(data, function (index, value) {
          var time = getTime(value, limit, property);
          line.push(time);
        });
        result.push(line);
      }
    });

    return result;
  }

  function getTime(value, limit, property) {
    return Math.floor(((value.values || {})[limit] || {})[property] || 0.0);
  }
}

$(document).ready(function () {
  loadGoogleCharts(loadJmhResult);
});

function loadGoogleCharts(onLoadCallback) {
  google.charts.load('current', {'packages': ['corechart']});
  google.charts.setOnLoadCallback(onLoadCallback);
}

function loadJmhResult() {
  var state = {};
  $.when(
    $.getJSON('jmh-results.json', function (list) {
      state.local = list;
    }),
    $.getJSON('jmh-results-wifi.json', function (list) {
      state.wifi = list;
    }),
    $.getJSON('jmh-results-wire.json', function (list) {
      state.wire = list;
    }),
    $.getJSON('jmh-results-strange.json', function (list) {
      state.strange = list;
    })
  ).then(function () {
    loadMysqlStreamingChartsPage(state);
  });
}
