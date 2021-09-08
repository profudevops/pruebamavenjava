import requestSrv from './index';

function getContentInfo() {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/contenido-home/obtener-contenido-home',
      method: 'GET',
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function saveContent(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/contenido-home/guardar-contenido-home',
      method: 'POST',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function saveImagesContent(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/contenido-home/subir-imagen-galeria',
      method: 'POST',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export {
  getContentInfo,
  saveContent,
  saveImagesContent,
};
