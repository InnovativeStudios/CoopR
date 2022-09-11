<script setup lang="ts">
import { RouterView } from "vue-router";
import ToolBar from "./components/ToolBar.vue";
</script>

<template>
  <div>
    <ToolBar />
    <RouterView />
  </div>
</template>
