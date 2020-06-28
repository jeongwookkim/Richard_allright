<template>
  <base-section
    id="features"
    class="secondary"
  >
    <v-responsive
      class="mx-auto"
      max-width="1350"
    >
      <v-container fluid>
        <v-row>
          <v-col
            v-for="card in cards"
            :key="card.image"
            cols="12"
            sm="4"
            md="3"
          >
            <base-info-card
              align="center"
              dark
              v-bind="card"
            />
          </v-col>
        </v-row>
      </v-container>
    </v-responsive>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionFeatures',

    data: () => ({
      cards: [
        {
          icon: 'mdi-trophy-award',
          title: 'Team Leader',
          text: 'Jeongwook Kim',
        },
        {
          icon: 'mdi-android',
          title: 'App Developer',
          text: 'Gihun Lee',
        },
        {
          icon: 'mdi-code-tags',
          title: 'Front-End developer',
          text: 'Minkyeong Kim',
        },
        {
          icon: 'mdi-memory',
          title: 'Full-Stack developer',
          text: 'Jihwan Kim',
        },
      ],
    }),
  }

</script>
<!--https://materialdesignicons.com/cdn/2.0.46/-->
