<template>
  <base-info-card title="Features">
    <a
      v-for="feature in features"
      :key="feature"
      :text="feature"
      class="mb-8 grey--text body-1 d-block text-none"
      href="#"
      v-html="`&rsaquo; ${feature}`"
    />
  </base-info-card>
</template>

<script>
  export default {
    name: 'SectionFeaturesAlt',

    data: () => ({
      features: [
        '편리성',
        '접근성',
        '합리적인 가격',
        '도로미관 개선',
      ],
    }),
  }
</script>
