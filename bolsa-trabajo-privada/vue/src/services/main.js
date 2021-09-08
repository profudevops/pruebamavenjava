import requestSrv from './index';

function getResultsFromPath(path, value = '', queryStringParam = '') {
  return new Promise((resolve, reject) => {
    const data = {};

    if (queryStringParam && value) {
      data[queryStringParam] = value;
    }

    requestSrv({
      path,
      method: 'GET',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

export default {
  getResultsFromPath,
};
