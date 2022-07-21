import EventBus from "@/eventBus";
import { Component, Vue } from "vue-property-decorator";

import { Menu } from "@/types";
import AtmStore from "../../store/atmStore";

@Component({})
export default class Footer extends Vue {
  menuEnum = Menu;

  okClicked(): void {
    EventBus.$emit("okBtnClicked");
  }

  homeClicked(menu: Menu): void {
    AtmStore.setSelectedMenu(menu);
    EventBus.$emit("homeBtnClicked");
  }
}
