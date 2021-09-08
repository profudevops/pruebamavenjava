export default (sectionCode, permission) => {
  const user = JSON.parse(sessionStorage.getItem('user'));

  return user.systemSections.filter(section => section.code === sectionCode && section.operations.includes(permission)).length > 0;
};
