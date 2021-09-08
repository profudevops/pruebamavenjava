import requestSrv from './index';
import cleanObject from '@/utils/cleanObject';

function getCandidates(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: 'candidatos/obtener-candidatos-en-reclutamiento',
      method: 'GET',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getProspectInfo(idCandidato) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/detalle-candidato',
      method: 'GET',
      data: {
        idCandidato,
      },
    })
      .then(res => resolve(res.data.entity))
      .catch(error => reject(error));
  });
}

function getProspectVacanciesInfo(idCandidato) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/obtener-historial-candidato',
      method: 'GET',
      data: {
        idCandidato,
      },
    })
      .then(res => resolve(res.data.entity))
      .catch(error => reject(error));
  });
}

function checkVacancyStatus(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-datos',
      method: 'GET',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

/*
 * reclutamiento/obtener-horas-disponibles
  {
    "codigoFase": "string",
    "fecha": "string",
    "idConsultor": "string",
    "idUsuario": "string",
    "tipoEntrevistador": 0
  }
 */
function getSchedules(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-horas-disponibles',
      method: 'POST',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getAvailableOperations(idReclutamiento) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-operaciones-disponibles-usuario',
      method: 'GET',
      data: {
        idReclutamiento,
      },
    })
      .then(res => resolve(res.data.entity))
      .catch(error => reject(error));
  });
}

function getHistoricalProcess(idReclutamiento, step) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-historial-proceso',
      method: 'GET',
      data: {
        idReclutamiento,
        step,
      },
    })
      .then(res => resolve(res.data.entity))
      .catch(error => reject(error));
  });
}

function getStageInfo(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-datos-fase',
      method: 'GET',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getDivisions(masterVacancy = null) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: 'reclutamiento/obtener-divisiones',
      data: { masterVacancy },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getInterviewDataByPhase({
  idEtapa, idReclutamiento = null, codigo = null, idEntrevista = null,
}) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-datos-entrevista-por-etapa',
      method: 'GET',
      data: {
        idEtapa,
        idReclutamiento,
        codigo,
        idEntrevista,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function saveRecruitmentInfo(data, stage) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/guardar-datos-fase',
      method: 'POST',
      data: cleanObject(data, stage),
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function discardProspect(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/guardar-datos-fase',
      method: 'POST',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function getDiscardReasons(codigo) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-motivos-rechazo-etapa',
      method: 'GET',
      data: {
        codigo,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getFormInfo(codigo) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/obtener-catalogos-etapa',
      method: 'GET',
      data: {
        codigo,
      },
    })
      .then(res => resolve(res.data.entity))
      .catch(error => reject(error));
  });
}

function getVacanciesForAssignment() {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/obtener-lista-simple-mis-vacantes',
      method: 'GET',
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function getVacanciesForRecommendation() {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/obtener-lista-simple-otras-vacantes',
      method: 'GET',
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function assignToOpportunity(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/asignar-vacante',
      method: 'POST',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function recommendToOpportunity(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/recomendar-vacante',
      method: 'POST',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function acceptDocument(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: 'https://reqres.in/api/users',
      method: 'POST',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function rejectDocument(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: 'https://reqres.in/api/users',
      method: 'POST',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function candidateFeedback(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/calificar-candidato',
      method: 'POST',
      data,
    })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function getFeedbackTags(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/obtener-calificaciones-tags',
      method: 'GET',
      data,
    })
      .then(({ data: { entity } }) => {
        const tags = entity.filter(e => e.codigo).map((item) => {
          switch (item.codigo) {
            case 'FV':
              item.icono = 'star';
              item.tipo = 'fit';
              break;
            case 'VB':
              item.icono = 'smile';
              item.tipo = 'fit';
              break;
            case 'NV':
              item.icono = 'ban';
              item.tipo = 'not-fit';
              break;
            case 'LN':
              item.icono = 'frown';
              item.tipo = 'not-fit';
              break;
            default:
              break;
          }

          return item;
        }).sort((a, b) => a.tipo.localeCompare(b.tipo));

        resolve(tags);
      })
      .catch(error => reject(error));
  });
}

function getAvailableFolios(folio) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/reclutamiento/buscar-folio',
      method: 'GET',
      data: {
        folio: folio.toUpperCase(),
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function updateProspectEmail(idCandidato, email) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/editar-correo',
      method: 'POST',
      data: {
        idCandidato,
        correo: email,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function resendFormNotification(codigo, modo, idCandidato, idReclutamiento) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/notificaciones/reenviar-notificacion',
      method: 'POST',
      data: {
        codigo,
        modo,
        idCandidato,
        idReclutamiento,
      },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export default {
  getCandidates,
  getProspectInfo,
  getProspectVacanciesInfo,
  checkVacancyStatus,
  getAvailableOperations,
  getHistoricalProcess,
  getStageInfo,
  getSchedules,
  saveRecruitmentInfo,
  discardProspect,
  getDiscardReasons,
  getFormInfo,
  getDivisions,
  getVacanciesForAssignment,
  getVacanciesForRecommendation,
  assignToOpportunity,
  recommendToOpportunity,
  acceptDocument,
  rejectDocument,
  candidateFeedback,
  getInterviewDataByPhase,
  getFeedbackTags,
  getAvailableFolios,
  updateProspectEmail,
  resendFormNotification,
};
