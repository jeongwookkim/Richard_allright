<template>
  <base-section id="theme-features">
    <base-section-heading title="서비스 장점">
      우리 서비스의 6가지 장점
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
            {{feature.text}}
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
          icon: 'mdi-fountain-pen-tip',
          text: '편리합니다.'
        },
        {
          title: '접근성',
          icon: 'mdi-cellphone',
          text: '접근성이 좋습니다.'
        },
        {
          color: 'primary',
          dark: true,
          title: '가성비',
          icon: 'mdi-pencil-box-outline',
          text: '가격이 착합니다.'
        },
        {
          title: '기훈이 바보',
          icon: 'mdi-image-size-select-actual',
          text: '그냥 바보 입니다.'
        },
        {
          title: '민경이도 바보',
          icon: 'mdi-ice-pop',
          text: '전주 바보 입니다.'
        },
        {
          title: '정욱이형도 바보',
          icon: 'mdi-help-circle-outline',
          text: '그 바보'
        },
      ],
    }),
  }
</script>
