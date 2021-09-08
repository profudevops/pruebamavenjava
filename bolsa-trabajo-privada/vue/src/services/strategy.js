import reqService from '@/services/index';

function createStrategy(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-estrategia', method: 'POST', data })
      .then((response) => {
        resolve(response);
      });
  });
}

function updateStrategy(data) {
  return new Promise((resolve, reject) => {
    reqService({ path: 'vacantes/guardar-estrategia', method: 'POST', data })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function createMasterStrategy(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-estrategia-vacante-maestra', method: 'POST', data })
      .then((response) => {
        resolve(response);
      });
  });
}

function updateMasterStrategy(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-estrategia-vacante-maestra', method: 'POST', data })
      .then((response) => {
        resolve(response);
      });
  });
}

function getStrategy(idPublicacion) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-estrategia', data: { idPublicacion } })
      .then(({ data }) => {
        resolve(data);
      });
  });
}

function getMasterStrategy(idPublicacion) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-estrategia-vacante-maestra', data: { idPublicacion } })
      .then(({ data }) => {
        resolve(data);
      });
  });
}

function getExternalJobBoards() {
  return new Promise((resolve, reject) => {
    reqService({ path: 'vacantes/obtener-bolsas-externas-por-rama' })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export {
  createStrategy,
  updateStrategy,
  createMasterStrategy,
  updateMasterStrategy,
  getStrategy,
  getMasterStrategy,
  getExternalJobBoards,
};
