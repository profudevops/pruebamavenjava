/* eslint-disable */
// Base64 to Blob
export default function base64toBlob(base64Data, contentType, sliceSize) {

  var byteCharacters,
      byteArray,
      byteNumbers,
      blobData,
      blob;

  contentType = contentType || '';

  byteCharacters = atob(base64Data);

  // Get blob data sliced or not
  blobData = sliceSize ? getBlobDataSliced() : getBlobDataAtOnce();

  blob = new Blob(blobData, { type: contentType });

  return blob;


  /*
   * Get blob data in one slice.
   * => Fast in IE on new Blob(...)
   */
  function getBlobDataAtOnce() {
      byteNumbers = new Array(byteCharacters.length);

      for (var i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
      }

      byteArray = new Uint8Array(byteNumbers);

      return [byteArray];
  }

  /*
   * Get blob data in multiple slices.
   * => Slow in IE on new Blob(...)
   */
  function getBlobDataSliced() {

      var slice,
          byteArrays = [];

      for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
          slice = byteCharacters.slice(offset, offset + sliceSize);

          byteNumbers = new Array(slice.length);

          for (var i = 0; i < slice.length; i++) {
              byteNumbers[i] = slice.charCodeAt(i);
          }

          byteArray = new Uint8Array(byteNumbers);

          // Add slice
          byteArrays.push(byteArray);
      }

      return byteArrays;
  }
}
