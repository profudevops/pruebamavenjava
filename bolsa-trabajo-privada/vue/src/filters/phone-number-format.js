export default function (phoneNumberString) {
  const cleaned = phoneNumberString.toString().substring(0, 10).replace(/\D/g, '');
  const match = (phoneNumberString.length >= 10) ? cleaned.match(/^(\d{2})(\d{4})(\d{4})$/) : cleaned.match(/^(\d{4})(\d{4})$/);

  if (match) {
    if (phoneNumberString.length >= 10) {
      return `${match[1]} ${match[2]}-${match[3]}`;
    }
    return `${match[1]}-${match[2]}`;
  }

  return null;
}
