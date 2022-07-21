import { Component, Vue } from "vue-property-decorator";
import EventBus from "@/eventBus";

import AtmStore from "../../store/atmStore";
import { Menu } from "@/types";

@Component({})
export default class Footer extends Vue {

    menuEnum = Menu;

    okClicked(): void {
        EventBus.$emit('okBtnClicked');
    }

    homeClicked(menu: Menu): void {
        AtmStore.setSelectedMenu(menu);
        EventBus.$emit('homeBtnClicked');
    }

}