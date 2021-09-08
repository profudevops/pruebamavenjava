export default function getYesterdayDate() {
  const date = new Date();

  date.setDate(date.getDate() - 1);

  return date;
}
