import requestSrv from './index';

function getAreaList() {
  return new Promise((resolve, reject) => {
    requestSrv({ path: 'areaForm/obtener-areas-interes' })
      .then(res => resolve(res))
      .catch(error => reject(error));
  });
}

function getArea(idArea) {
  return new Promise((resolve) => {
    requestSrv({ path: 'areaForm/obtener-area-interes', data: { idArea } })
      .then((res) => {
        resolve(res);
      });
  });
}

function deleteArea(id) {
  return new Promise((resolve) => {
    requestSrv({ path: 'areaForm/baja-area-de-interes', method: 'POST', data: { id } })
      .then((res) => {
        resolve(res);
      });
  });
}

function editSubarea(idArea, idSubArea, nombre) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: 'areaForm/actualizar-sub-area-interes',
      method: 'POST',
      data: {
        idArea, idSubArea, descripcion: '', nombre,
      },
    })
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function deleteSubarea(idSubArea, idArea) {
  return new Promise((resolve, reject) => {
    requestSrv({ path: 'areaForm/baja-subarea-de-interes', method: 'POST', data: { idSubArea, idArea } })
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function createArea(data) {
  return new Promise((resolve, reject) => {
    requestSrv({ path: 'areaForm/crear-area-interes', method: 'POST', data })
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function updateArea(data) {
  return new Promise((resolve, reject) => {
    requestSrv({ path: 'areaForm/actualizar-area-interes', method: 'POST', data })
      .then((res) => {
        resolve(res);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

export default {
  getAreaList,
  getArea,
  deleteArea,
  editSubarea,
  deleteSubarea,
  createArea,
  updateArea,
};
