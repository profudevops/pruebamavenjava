import moment from 'moment';

export default function (name, type) {
  return `${name}${moment().format(
    'YYYY-MM-DD HH:mm:ss',
  )}.${type}`;
}
