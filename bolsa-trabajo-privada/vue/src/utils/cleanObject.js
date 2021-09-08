export default (data, wrapper = false) => {
  const clonedData = JSON.parse(JSON.stringify(data));

  if (wrapper) {
    Object.keys(clonedData[wrapper]).forEach(key => (clonedData[wrapper][key] === null || key.indexOf('Clean') > 0) && delete clonedData[wrapper][key]);
  } else {
    Object.keys(clonedData).forEach(key => (clonedData[key] === null || key.indexOf('Clean') > 0) && delete clonedData[key]);
  }

  return clonedData;
};
