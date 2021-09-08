export default function (fileURL, fileName, external) {
  const downloadLink = document.createElement('a');

  if (external) {
    downloadLink.target = '_blank';
  }

  downloadLink.href = fileURL;
  downloadLink.download = fileName;
  document.body.appendChild(downloadLink);
  downloadLink.click();
  document.body.removeChild(downloadLink);
}
