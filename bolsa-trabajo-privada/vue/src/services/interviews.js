import requestSrv from './index';

function getInterviews(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/entrevistas/obtener-entrevistas',
      method: 'GET',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export default {
  getInterviews,
};
