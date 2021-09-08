import Vue from 'vue';

function isUserGranted(value) {
  const user = JSON.parse(sessionStorage.getItem('user'));
  const allowed = [];

  value.forEach((item) => {
    const currentPermission = item.split(/_(.+)/).filter(x => x);
    const sectionName = currentPermission[0];
    const permission = currentPermission[1];

    if ((user.systemSections.filter(section => section.code === sectionName).length > 0 && !permission)
      || user.systemSections.filter(section => section.code === sectionName && section.operations.includes(permission)).length > 0) {
      allowed.push(true);
    }
  });

  return allowed.length > 0;
}

export default Vue.directive('permission', (el, binding, vnode) => {
  if (!isUserGranted(binding.value)) {
    const comment = document.createComment(' ');
    Object.defineProperty(comment, 'setAttribute', {
      value: () => undefined,
    });
    vnode.elm = comment;
    vnode.text = ' ';
    vnode.isComment = true;
    vnode.context = undefined;
    vnode.tag = undefined;
    vnode.data.directives = undefined;

    if (vnode.componentInstance) {
      vnode.componentInstance.$el = comment;
    }

    if (el.parentNode) {
      el.parentNode.replaceChild(comment, el);
    }
  }
});
