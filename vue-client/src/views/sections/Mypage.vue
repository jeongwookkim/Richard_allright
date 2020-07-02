<template>
  <base-section
    id="mypage"
  >
    <base-section-heading title="MyPage" />

    <v-container>
      <v-row
        align="center"
        justify="center"
      >
        <v-img
          max-width="20%"
          :user-photo-url="userPhotoUrl"
          :src="userPhotoUrl"
        />
        <v-col
          cols="12"
          md="7"
        >
          <v-card
            elevation="16"
            class="mb-12"
          >
            <v-card-title>My ParkingLot</v-card-title>
            <v-card-text>
              <div
                v-for="parkingLot in myParkingLots"
                :key="parkingLot.timestamp"
              >
                주문 번호: {{ parkingLot.orderNumber }} 주차장 이름: {{ parkingLot.parkingLotName }}
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  import firebase from '../../../firebase/init'
  import cookies from 'vue-cookies'

  const userUid = cookies.get('user_uid')
  export default {
    name: 'MyPage',

    data: () => ({
      userPhotoUrl: cookies.get('userPhotoUrl'),
      myParkingLots: [],
    }),
    mounted () {
      firebase
        .firestore()
        .collection('usedParkingLot')
        .where('uid', '==', userUid)
        .get()
        .then(snapshot => {
          snapshot.forEach(doc => {
            console.log('doc는')
            console.log(doc.data())
            this.myParkingLots.push({ parkingLotName: doc.data().parkingLotName, orderNumber: doc.data().orderNumber, timestamp: doc.data().timestamp })
          })
        })
        .catch(err => {
          console.log('Error getting documents', err)
        })
    },
    methods: {
      // byeCar (orderNumber) {
      //   firebase
      //     .firestore()
      //     .collection('usedParkingLot')
      //     .where(orderNumber)
      //     .delete()
      //     .then(response => {
      //       console.log(response)
      //     })
      //     .catch(err => {
      //       console.log('문서 삭제 에러용', err)
      //     })
      // },
    },
  }
</script>
