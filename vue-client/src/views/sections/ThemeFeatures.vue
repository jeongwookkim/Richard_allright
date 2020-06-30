<template>
  <base-section id="theme-features">
    <base-section-heading title="Our Service">
      <!-- 우리 서비스의 6가지 장점 -->
    </base-section-heading>
    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in features"
          :key="i"
          cols="12"
          md="6"
        >
          <base-avatar-card
            v-bind="feature"
            align="left"
            horizontal
          >
            {{ feature.text }}
          </base-avatar-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionThemeFeatures',

    data: () => ({
      features: [
        {
          title: '편리성',
          icon: 'mdi-home-map-marker',
          text: '가까운 원룸을 찾고, 주차하세요.',
        },
        {
          title: '접근성',
          icon: 'mdi-human',
          text: '우리는 어디에나 있습니다.',
        },
        {
          color: 'primary',
          dark: true,
          title: '가성비',
          icon: 'mdi-currency-usd',
          text: '공용주차장에 비해 저렴합니다',
        },
        {
          title: '',
          icon: 'mdi-image-size-select-actual',
          text: '그냥 바보 입니다.',
        },
        // {
        //   title: '민경이도 바보',
        //   icon: 'mdi-ice-pop',
        //   text: '전주 바보 입니다.',
        // },
        // {
        //   title: '정욱이형도 바보',
        //   icon: 'mdi-help-circle-outline',
        //   text: '그 바보',
        // },
      ],
    }),
  }
</script>
<style>
base-section-heading{
  /* text-transform: uppercase; */
  font-size: 10px;
}
</style>
