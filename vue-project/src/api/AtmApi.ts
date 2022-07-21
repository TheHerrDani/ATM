import { Vue } from "vue-property-decorator";
import CallHandler from "./CallHandler";
import AtmStore from "../store/atmStore";
import AtmDataModel from "@/types/AtmDataModel";
import callHandlerStore from "@/store/callHandlerStore";
import Axios from "axios-observable";

export default class AtmApi extends Vue {
  private static atmApiInstance: AtmApi;

  get databaseHandler(): Axios | null {
    return callHandlerStore.getCallHandler;
  }

  public async setAtmStateInformation(atmId: number) {
    if (this.databaseHandler) {
      try {
        const params = { params: { atmId: atmId } };
        this.databaseHandler
          .get<AtmDataModel>("/api/atm/information", params)
          .subscribe({
            next: (response) => AtmStore.setAtmStateInformation(response.data),
            error: (error) => console.error(error),
          });
      } catch (exp) {
        console.error(exp);
      }
    }
  }

  static getAtmApiInstance(): AtmApi {
    if (AtmApi.atmApiInstance) {
      return AtmApi.atmApiInstance;
    } else {
      return (AtmApi.atmApiInstance = new AtmApi());
    }
  }
}
