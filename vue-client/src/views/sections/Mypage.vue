<template>
  <base-section
    id="mypage"
  >
    <v-img
      :src="userPhotoUrl"
      class="mx-auto mb-8"
      max-width="128"
    />

    <base-section-heading title="MyPage" />

    <v-container>
      <v-row
        align="center"
        justify="center"
      >
        <v-col
          cols="12"
          md="7"
        >
          <p>사용자 정보를 입력 합시다</p>
        </v-col>

        <v-col
          cols="12"
          md="5"
        >
          <v-card
            elevation="16"
            class="mb-12"
          >
            <base-img
              max-width="100%"
              :src="userPhotoUrl"
            />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    name: 'MyPage',

    data: () => ({
    }),
    computed: {
      ...mapGetters(['userPhotoUrl']),
    },
  }
</script>
