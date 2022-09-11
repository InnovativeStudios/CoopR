<script setup lang="ts">
  const links = [
    {
      name: 'Home',
      href: '/'
    },
    {
      name: 'About',
      href: '/about'
    }
  ]
</script>

<template>
  <div class="bg-zinc-800 p-3">
    <nav class="flex flex-wrap items-center max-w-7xl mx-auto p-3">
      <RouterLink class="text-white hover:text-gray-400 no-underline text-lg uppercase mr-2" :to="link.href" v-for="link in links">{{ link.name }}</RouterLink>
    </nav>
  </div>
</template>