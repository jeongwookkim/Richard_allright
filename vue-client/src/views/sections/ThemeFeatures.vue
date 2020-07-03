<template>
  <base-section id="theme-features">
    <base-section-heading title="서비스 장점">
      우리 서비스의 4가지 장점을 소개합니다.
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
          icon: 'mdi-fountain-pen-tip',
          text: '클릭 한번으로 원한는 주차장을 예약하세요.',
        },
        {
          title: '접근성',
          icon: 'mdi-cellphone',
          text: '방문하시는 건물 주변에 주차가 가능합니다.',
        },
        {
          color: 'primary',
          dark: true,
          title: '합리적인 가격',
          icon: 'mdi-pencil-box-outline',
          text: '비싼 공영주차장말고 합리적인 가격으로 주차하세요.',
        },
        {
          title: '도로미관 개선',
          icon: 'mdi-image-size-select-actual',
          text: '더 이상 도로변 불법 주차는 NO!',
        },
      ],
    }),
  }
</script>
