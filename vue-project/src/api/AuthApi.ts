import { Vue } from "vue-property-decorator";
import CallHandler from "./CallHandler";
import Axios from "axios-observable";
import TokenStore from "@/store/TokenStore";
import CallHandlerStore from "@/store/callHandlerStore";
import { AxiosRequestConfig } from "axios";
import AccountApi from "./AccountApi";
import atmStore from "@/store/atmStore";
import AtmApi from "./AtmApi";

export default class AuthApi extends Vue {
  private static authApiInstance: AuthApi;

  public async login(accountNumber: string, pin: string) {
    try {
      const databaseHandler = Axios.create({
        baseURL: "http://localhost:8080/",
        timeout: 1000,
      });
      databaseHandler
        .post<string>(
          "/api/login/create-token",
          {},
          { auth: { username: accountNumber, password: pin } }
        )
        .subscribe({
          next: async (response) => {
            await TokenStore.setToken(response.data);
            CallHandler.createCallHandler(TokenStore.getHeaderConfig).then(
              async (response) => {
                await CallHandlerStore.setCallHandler(response);
                AccountApi.getAccountApiInstance().information(atmStore.getAccountNumber);
                AtmApi.getAtmApiInstance().setAtmStateInformation(atmStore.getATM_ID);
              }
            );
          },
          error: (error) =>{ 
            console.error(error);
            TokenStore.setLoginError(error.message);
          },
        });
    } catch (exp) {
      console.error(exp);
    }
  }

  static getAuthApiInstance(): AuthApi {
    if (AuthApi.authApiInstance) {
      return AuthApi.authApiInstance;
    } else {
      return (AuthApi.authApiInstance = new AuthApi());
    }
  }
}
