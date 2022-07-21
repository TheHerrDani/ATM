import { Component, Vue } from "vue-property-decorator";

import AtmStore from "../../store/atmStore";

@Component({})
export default class Header extends Vue {
  get selectedMenu() {
    return AtmStore.getSelectedMenu;
  }
}
