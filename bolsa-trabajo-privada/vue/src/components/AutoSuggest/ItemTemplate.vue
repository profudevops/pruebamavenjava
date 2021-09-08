<template>
  <div>
    {{ item.nombre }} {{ item.apellido }}<em>{{ item.email }}</em>
  </div>
</template>

<script>
export default {
  props: {
    item: {
      required: true,
    },
    searchText: {
      required: true,
    },
  },
};
</script>
