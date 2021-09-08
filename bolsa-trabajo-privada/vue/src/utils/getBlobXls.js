export default function (field) {
  return window.URL.createObjectURL(new Blob([field]), {
    type:
      'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
  });
}
