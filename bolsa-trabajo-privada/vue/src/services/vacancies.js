import reqService from '@/services/index';
import cleanObject from '@/utils/cleanObject';

function getMasterVacancies() {
  return new Promise((resolve, reject) => {
    reqService({
      path: '/vacantes/obtener-vacantes-maestras',
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getPositionLevel() {
  return new Promise((resolve, reject) => {
    reqService({
      path: '/vacantes/obtener-niveles-puesto',
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

/*
 * POST 'vacantes/editar-plazas'
 * "numeroPlazas": Number
 * "idPublicacion;": Number
 */
function editSpot(data) {
  return new Promise((resolve, reject) => {
    reqService({ path: 'vacantes/editar-plazas', method: 'POST', data })
      .then((response) => {
        resolve(response);
      })
      .catch(error => reject(error));
  });
}

function masterVacancyDetail(data) {
  return new Promise((resolve, reject) => {
    reqService({
      path: '/vacantes/obtener-resumen-vacante-maestra',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getVacancies(data) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-todo',
      data,
    })
      .then(res => resolve(res.data))
      .catch(() => reject());
  });
}

function getVacancyDetail(idPublicacion = null, idVacante = null, isCloser = null) {
  let infoReq = {};
  const urlReq = (isCloser) ? 'detalle-vacante-cerrada' : 'detalle-vacante';

  if (idVacante) {
    infoReq = {
      ...infoReq,
      idVacante,
    };
  }

  if (idPublicacion) {
    infoReq = {
      ...infoReq,
      idPublicacion,
    };
  }

  return new Promise((resolve, reject) => {
    reqService({ path: `vacantes/${urlReq}`, data: infoReq })
      .then(({ data }) => {
        resolve(data);
      })
      .catch(error => reject(error));
  });
}


function getPostulates(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-candidatos', data })
      .then(res => resolve(res.data));
  });
}

function getJobBoardByIdDivision() {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-bolsas-externas-por-rama', data: {} })
      .then(({ data }) => {
        resolve(data);
      });
  });
}

function getSuggestCandidates(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-sugerencias-candidatos', data })
      .then(res => resolve(res.data));
  });
}

function getRecutrings() {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/buscar-colaboradores' })
      .then(({ data }) => {
        resolve(data);
      });
  });
}

function getVacancy(idVacante) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-vacante', data: { idVacante } })
      .then(({ data }) => {
        resolve(data);
      });
  });
}

function getMasterVacancy(idVacante) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/obtener-vacante-maestra', data: { idVacante } })
      .then(({ data }) => {
        resolve(data);
      });
  });
}

function createVacancy(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-vacante', method: 'POST', data })
      .then((response) => {
        resolve(response);
      });
  });
}

function getStates() {
  return new Promise((resolve, reject) => {
    reqService({ path: 'vacantes/estados-mexicanos' })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getHeadquarters(idGerencia) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-sedes',
      data: {
        idGerencia,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getAnalysts() {
  return new Promise((resolve, reject) => {
    reqService({ path: 'vacantes/buscar-colaboradores' })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function activeSquare(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-vacante', method: 'POST', data })
      .then(res => resolve(res.data));
  });
}

function getPublicationInfo(data) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-contenido-publicacion-vacante-maestra',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function withObserver({ idVacante, idPublicacion }) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/mostrar-buscar-observador',
      data: {
        idVacante,
        idPublicacion,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function savePublication(data) {
  return new Promise((resolve, reject) => {
    reqService({ path: 'vacantes/guardar-publicacion-vacante-maestra', method: 'POST', data: cleanObject(data) })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function updateVacancy(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-vacante', method: 'POST', data })
      .then((response) => {
        resolve(response);
      });
  });
}
function updateMasterVacancy(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/guardar-vacante-maestra', method: 'POST', data })
      .then((response) => {
        resolve(response);
      });
  });
}

function asignVacancy(idEmpleado, idPublicacion) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/asignar-responsabilidad-publicacion', method: 'POST', data: { idEmpleado, idPublicacion } })
      .then((response) => {
        resolve(response);
      });
  });
}

function getInterestAreas() {
  return new Promise((resolve) => {
    reqService({ path: 'areasInteres/obtener-areas' })
      .then(res => resolve(res.data));
  });
}

function getSubInterestArea(data) {
  return new Promise((resolve) => {
    reqService({ path: 'areasInteres/obtener-area', data })
      .then(res => resolve(res.data));
  });
}

function getJobBoardInfo(data) {
  return new Promise((resolve) => {
    reqService({ path: 'vacantes/generar-contenido-por-bolsa', data })
      .then(res => resolve(res.data));
  });
}

function confirmCloseVacancy(idPublicacion) {
  return new Promise((resolve) => {
    reqService({
      path: 'vacantes/cerrar-vacante',
      data: {
        idPublicacion,
      },
    })
      .then(res => resolve(res.data));
  });
}

function initCandidateProcess(data) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/iniciar-proceso',
      method: 'POST',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function saveDiscardProspect(data) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/descartar',
      method: 'POST',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getAgents({ idSede = '', idVacante = '' }) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-publicaciones',
      method: 'POST',
      data: {
        idSede,
        idVacante,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getDivisions() {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-divisiones',
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getRegional(idDivision) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-regiones',
      data: {
        idDivision,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getManagement(idRegional) {
  return new Promise((resolve, reject) => {
    reqService({
      path: 'vacantes/obtener-gerencias',
      data: {
        idRegional,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export {
  getMasterVacancies,
  masterVacancyDetail,
  getVacancy,
  getMasterVacancy,
  getVacancies,
  getPositionLevel,
  createVacancy,
  updateVacancy,
  updateMasterVacancy,
  getVacancyDetail,
  getPostulates,
  getJobBoardByIdDivision,
  getSuggestCandidates,
  getStates,
  getHeadquarters,
  getAnalysts,
  activeSquare,
  getPublicationInfo,
  withObserver,
  savePublication,
  asignVacancy,
  getRecutrings,
  getInterestAreas,
  getSubInterestArea,
  getJobBoardInfo,
  confirmCloseVacancy,
  initCandidateProcess,
  saveDiscardProspect,
  getDivisions,
  getAgents,
  editSpot,
  getRegional,
  getManagement,
};
