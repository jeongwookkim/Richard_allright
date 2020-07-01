<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Approve',

    metaInfo: { title: 'Approve' },

    extends: View,

    mixins: [
      LoadSections([
        'approve',
        'info-alt',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'approve',
      },
    },
  }
</script>
