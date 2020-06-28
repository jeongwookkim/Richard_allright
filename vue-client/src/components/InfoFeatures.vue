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
        '가성비',
        '너무',
        '졸려요',
        '흑흑흑',
      ],
    }),
  }
</script>
