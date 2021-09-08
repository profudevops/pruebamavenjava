const getReverseWord = (text) => {
  return text.split('').reverse().join('');
}

export default function (text) {
  const reverseWord = getReverseWord(text);
  const pointer = reverseWord.indexOf('.');
  return getReverseWord(reverseWord.substr(0, pointer));
}
