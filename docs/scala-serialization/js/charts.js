function loadScalaSerializationChartsPage(rawList) {
    var DataSizesToView = ['1k', '2k', '4k', '8k'];
    // ordered according to view
    var Libraries = [
        {
            name: 'Json',
            color: '#f4cccc',
            excludeFromMax: true
        },
        {
            name: 'ScalaPb',
            color: '#ff0000',
            excludeFromMax: false
        },
        {
            name: 'JavaPb',
            color: '#cc0000',
            excludeFromMax: false
        },
        {
            name: 'JavaThrift',
            color: '#e69138',
            excludeFromMax: false
        },
        {
            name: 'Scrooge',
            color: '#ff9900',
            excludeFromMax: false
        },
        {
            name: 'BooPickle',
            color: '#6aa84f',
            excludeFromMax: false
        },
        {
            name: 'Chill',
            color: '#0000ff',
            excludeFromMax: true
        },
        {
            name: 'Pickling',
            color: '#cccccc',
            excludeFromMax: true
        },
        {
            name: 'JavaSerialization',
            color: '#000000',
            excludeFromMax: true
        }
    ];
    var LibrariesToExcludeFromMax = createLibrariesToExcludeFromMax();

    var data = convertData(rawList);

    $('.ssa-value-btn').click(function () {
        var property = $(this).attr('data-property');

        buildChartAndRawData({
            elem: '.site-both',
            title: 'Two-way times',
            subtitle: 'site, nanos',
            maxTimeActions: ['both'],
            action: 'both',
            dataType: 'site',
            property: property
        });

        buildChartAndRawData({
            elem: '.events-both',
            title: 'Two-way times',
            subtitle: 'events, nanos',
            maxTimeActions: ['both'],
            action: 'both',
            dataType: 'events',
            property: property
        });

        buildChartAndRawData({
            elem: '.site-serialization',
            title: 'Serialization times',
            subtitle: 'site, nanos',
            maxTimeActions: ['serialization', 'deserialization'],
            action: 'serialization',
            dataType: 'site',
            property: property
        });

        buildChartAndRawData({
            elem: '.site-deserialization',
            title: 'Deserialization times',
            subtitle: 'site, nanos',
            maxTimeActions: ['serialization', 'deserialization'],
            action: 'deserialization',
            dataType: 'site',
            property: property
        });

        buildChartAndRawData({
            elem: '.events-serialization',
            title: 'Serialization times',
            subtitle: 'events, nanos',
            maxTimeActions: ['serialization', 'deserialization'],
            action: 'serialization',
            dataType: 'events',
            property: property
        });

        buildChartAndRawData({
            elem: '.events-deserialization',
            title: 'Deserialization times',
            subtitle: 'events, nanos',
            maxTimeActions: ['serialization', 'deserialization'],
            action: 'deserialization',
            dataType: 'events',
            property: property
        });

        $('.ssa-value-btn').removeClass('active');
        $(this).addClass('active');
    });

    $('.ssa-value-btn[data-property=avg]').click();

    function buildChartAndRawData(opts) {
        var maxTime = getMaxTime(opts.maxTimeActions, opts.dataType, opts.property);
        buildChart(opts.elem, opts.title, opts.subtitle, maxTime, getDataForChart(opts.action, opts.dataType, opts.property));
        buildRawData(opts.elem + '-raw-data', opts.action, opts.dataType, opts.property)
    }

    function buildChart(elem, title, subtitle, maxTime, dataArray) {
        var chartData = google.visualization.arrayToDataTable(dataArray);

        var series = {};
        $.each(Libraries, function(index, lib) {
            series[index] = {
                color: lib.color
            }
        });

        var options = {
            chart: {
                title: title,
                subtitle: subtitle
            },
            series: series,
            vAxis: {
                viewWindow: {
                    max: maxTime
                }
            }
        };

        var chart = new google.charts.Bar($(elem).get(0));

        chart.draw(chartData, google.charts.Bar.convertOptions(options));
    }

    function buildRawData(elem, action, dataType, property) {
        var table = $('<table>')
            .addClass('table table-striped table-condensed');

        var head = $('<tr>');
        head.append($('<th>').html('Library \\ Data Size'));
        $.each(DataSizesToView, function(index, dataSize) {
            head.append($('<th>').html(dataSize));
        });
        table.append($('<thead>').append(head));

        var tbody = $('<tbody>');
        $.each(data, function(index, value) {
            var tr = $('<tr>');
            function addTd(text) {
                tr.append($('<td>').html(text));
            }

            addTd(value.name);

            $.each(DataSizesToView, function(index, dataSize) {
                addTd(getTime(value, action, dataType, dataSize, property));
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
        var map = {};

        $.each(list, function (index, entry) {
            var t = extractFromBenchmark(entry.benchmark);
            var pm = entry.primaryMetric;

            var timeByDataSize = getOrUpdate(
                getOrUpdate(
                    getOrUpdate(map, t.name, {name: t.name, values: {}}).values,
                    t.action
                ),
                t.dataType
            );
            timeByDataSize[t.dataSize] = {
                avg: pm.score,
                p0: pm.scorePercentiles['0.0'],
                p50: pm.scorePercentiles['50.0'],
                p95: pm.scorePercentiles['95.0'],
                p100: pm.scorePercentiles['100.0']
            };
        });

        var result = [];
        $.each(Libraries, function(index, lib) {
            result.push(map[lib.name]);
        });

        return result;
    }

    function extractFromBenchmark(benchmark) {
        //'com.komanov.serialization.jmh.ScalaPbBenchmark.deserialization_events_1k'

        var benchmarkParts = benchmark.split('Benchmark.');
        if (benchmarkParts.length != 2) {
            throw new Error('Expected 2 parts in a benchmark: ' + benchmark);
        }

        var typeParts = benchmarkParts[1].split('_');
        if (typeParts.length != 3) {
            throw new Error('Expected 3 type parts in a benchmark: ' + benchmark);
        }

        return {
            name: benchmarkParts[0].substring(benchmarkParts[0].lastIndexOf('.') + 1),
            action: typeParts[0],
            dataType: typeParts[1],
            dataSize: typeParts[2]
        };
    }

    function getMaxTime(actions, dataType, property) {
        var max = 0;
        $.each(DataSizesToView, function (index, dataSize) {
            $.each(data, function (index, value) {
                if (LibrariesToExcludeFromMax[value.name] !== true) {
                    $.each(actions, function (index, action) {
                        max = Math.max(max, getTime(value, action, dataType, dataSize, property));
                    });
                }
            });
        });
        // max, excluding very long, + 10%
        // should be the same for all charts to better UX (comparison)
        return max + (max / 10);
    }

    function getDataForChart(action, dataType, property) {
        var result = [];

        var header = ['data size'];
        $.each(data, function (index, value) {
            header.push(value.name);
        });
        result.push(header);

        $.each(DataSizesToView, function (index, dataSize) {
            var line = [dataSize];
            $.each(data, function (index, value) {
                var time = getTime(value, action, dataType, dataSize, property);
                line.push(time);
            });
            result.push(line);
        });

        return result;
    }

    function getTime(value, action, dataType, dataSize, property) {
        return Math.floor((((value.values[action] || {})[dataType] || {})[dataSize] || {})[property] || 0.0);
    }

    function createLibrariesToExcludeFromMax() {
        var map = {};
        $.each(Libraries, function (index, value) {
            map[value.name] = value.excludeFromMax;
        });
        return map;
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
