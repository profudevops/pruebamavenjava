import axios from 'axios';
import store from '../store/index';
import authSrv from '../services/auth';

export default function setupInterceptors() {
  // Request
  axios.interceptors.request.use((response) => {
    const { token } = store.getters['auth/token'];

    if (token) {
      response.headers.Authorization = `Bearer ${token}`;
    } else if (sessionStorage.getItem('token')) {
      response.headers.Authorization = `Bearer ${sessionStorage.getItem('token')}`;
    }
    var sessionToken = sessionStorage.getItem('token');
    if(sessionToken !== undefined && sessionToken !== null && sessionToken !== token){
      response.headers.Authorization = `Bearer ${sessionStorage.getItem('token')}`;
    }

    return response;
  });

  // Response
  const responseInterceptor = axios.interceptors.response.use(
    response => response,
    (error) => {
      if (error.response.status !== 401) {
        return Promise.reject(error.response);
      }

      /*
      * When response code is 401, try to refresh the token.
      * Eject the interceptor so it doesn't loop in case
      * token refresh causes the 401 response
      * See: https://stackoverflow.com/questions/51646853/automating-access-token-refreshing-via-interceptors-in-axios
      */
      axios.interceptors.response.eject(responseInterceptor);

      /* return authSrv.refreshToken({
        email: 'eve.holt@reqres.in',
        password: 'cityslicka',
        refreshToken: 'newToken',
      })
        .then((res) => {
          const token = `${res.data.token}_CHANGED`;
          store.dispatch('auth/refreshToken', token);
          error.response.config.headers.Authorization = `Bearer ${token}`;

          return axios(error.response.config);
        })
        .catch((err) => {
          store.dispatch('auth/logout');

          return Promise.reject(err.response);
        })
        .finally(setupInterceptors); */
    },
  );
}
