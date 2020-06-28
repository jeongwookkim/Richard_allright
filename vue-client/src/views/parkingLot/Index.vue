<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'ParkingLot',

    metaInfo: { title: 'Search ParkingLot' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'parkingLotMap',
        'info',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'parkingLot',
      },
    },
  }
</script>
