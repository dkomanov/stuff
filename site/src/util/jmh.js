/**
 * Converts list.
 *
 * @param {Array} results Raw JMH output.
 * @param {function(benchmark: string, params: string)} extractor
 * @returns {Array} Converted list composed of extracted fields and pm (primaryMetric).
 */
export function exportDataTable(results, extractor) {
  return results.map(value => {
    const dimensions = extractor(value.benchmark, value.params);
    dimensions.pm = value.primaryMetric;
    return dimensions;
  });
}

/**
 * Convert data from list to a 2-dimensional array (table).
 *
 * @param {Array} list List of objects.
 * @param {function(pm)} extractByTimeUnitFunc Function to extract metric from primaryMetric object.
 * @param {{title: string, prop: string, values: [string|{name:string,value:string}]}} xDesc Descriptor for x-axis.
 * @param {{title: string, prop: string, values: [string|{name:string,value:string}]}} yDesc Descriptor for y-axis.
 * @returns {Array} 2-dimensional array (table) with data. First row is the header, 1 column (or 0) is header as well.
 */
export function buildData(list, extractByTimeUnitFunc, xDesc, yDesc) {
  const result = [];

  const header = [yDesc.title];
  xDesc.values.forEach(v => {
    const {name = v} = v;
    header.push(name);
  });
  result.push(header);

  yDesc.values.forEach(y => {
    const {name: yName = y, value: yValue = y} = y;
    const dataForThisLine = list.filter(v => v[yDesc.prop] === yValue);
    const line = [yName];
    xDesc.values.forEach(x => {
      const {value: xValue = x} = x;
      const d = dataForThisLine.find(v => v[xDesc.prop] === xValue) || {pm: {scorePercentiles: {}}};
      line.push(Math.floor(extractByTimeUnitFunc(d.pm) || NaN));
    });
    if (line.slice(1).filter(v => !Number.isNaN(v)).length > 0) {
      result.push(line);
    }
  });

  return result;
}
