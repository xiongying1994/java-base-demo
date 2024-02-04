package Base.httpUrl;

import net.sf.json.JSONObject;

/**
 * @Author: xiongying
 * @Date: 2024/2/4 10:01
 */
public class HttpMainTest {
    public static void main(String[] args) {
//        new HttpPostClientUtil("asd").doPost();

        String result = new HttpClientUtil().doGet("http://localhost:8081/dmp-service/userToken/getToken?userId=H21132414&phone=15234251236&privateKey=MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCNpKmvwyF%2F5gdDjgXO%2FeWd69CEH6GSYQOVMsDw0liCgHA4SyRXAg9r0TiwRMXiWPKEZ%2BI1R2JM1DjjCRZ8Jaj9CRJ34JOlTg7Y%2Ff0vSDGcIU7vGfW7tXeCEROvbJMNHoZJtwStAGcdwkZf%2BGJ%2F7HEnHsMKy4dieBfDHjqslliAvWQzAok%2BxuVWOjwY8gQg8qu%2BYCi5KGnObkacqvXl6vve3aqmrwAyBj%2F%2BmNnG6S72XpMwWDmjEK5qFp%2B1oZvrz8b0BbVbVimWfWnnHlOcSiTdGjtMqXX7GL3QK9eszjLH07TSmvw3m9IDaHoFb96T8%2FNjKI%2BQYCUO%2Bhm1nVUvw0CtAgMBAAECggEAbZ2G7EyYekBsUq1wdVYsAuDiB73q7jtB6I7UngpLEQOQ1R%2FhyawAfzH1KwSuF5M6a5lxiEXsHwUF2T3JNt52PwxsTHVyDbWw6%2BDztUmriuaEDaoLb%2B7pw7CviUz%2FKGH9DsMiTThIlNEqYU75%2B4Hht6Ko0ovcBCpoSUn1ombfqxC%2FPuzrJEXt4sgf11BC%2FZywPiYFFDKPzbJlNSw6mAOs1cRUwRl0qlzHesIdAIGL13Mnq5QvZXst0UTgCvPk2mkWH5PBiUk0yrQHA6TsjNM54pZru%2BEg%2F%2FLihfrE4%2F%2FOeL5qLMhONA6rrIyCRa68J7XcZtX9lcxO9LfMpz6e6JfGSQKBgQDLdyYc22Eo8lhjquIOUgdgjvKIfatb7vMN0A7On1LZ0O5mfvBC5Jfd89zTQoFOFeuBe%2F5xPla7FgakjgIpxtsMXJ0wtvleKKp0urRgTMIfMx3tJzjmoVGZRutAhUINq2WyzIqgKDZtgmaBmqLvL9uCV02cDwRSw7foCnOkdMEfLwKBgQCyNyFz8YUe3Rfe9EPlycCIJ7GwxuxFbLW6ZsjBJJ%2BEr4zeBtL4mgPSEaGl%2Baq8cFGjizQ%2BmRDz%2B3cDtMWdvkeYqJ70fWG9plT5HLn7nsIgJOvecXNxVSzWdO9AF00PB8p%2BdYp7%2F76NpSg3YQNrOXv3zSnC0WmwMBGesJE%2BlG6G4wKBgQCUhTn18JHA0P6Caneu2mP2MQKLbaiLaCZVm91jKFvTEbckbF63haPx6ZP1RmOa%2B1c44qj%2BQvIOiOp6bgrYMgctw%2BEecUIgYHDk2nDWptFA8xBST8IyX%2BlviJwrMdrgnY%2BT78j1VcU9NOXcj2OGU5nZUB8f3rpaK%2FHv2MlHb86VEwKBgGaoy2R2pDB7O9z66IHeDkJgW0Nx03jKfoS54Lsh1aIhXgwn8i%2ByyLCh53QsteWG7gA%2FmADXO6TQHmbMI2oSyjtuJuK%2FtyYAQsKdgaUodrr9icHBqLaIUiPXiFprEcCuxD7EhtmXILWHhfsgr0989aRxUrUe3LrZdczr%2BT8LUf%2FxAoGBAL2VveR%2Fe2SIdPm0U4lHPzrt3eMzrUPGGluv9ewTFSX7GJ0spSLVcXpJuZfYuejgqk3OihciXHG8%2FE9OG4jm86FXWf5YDfWene98N6bEEiZFqFtebjAbt8tQhzyQW0qYXD854Xkkh3m8%2B247DHAyq2EKzvaoHAqOwNbEDdGY%2FmrL");
        System.out.println("创建Token接口返回：" + result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String accessJson = jsonObject.getString("data");
        System.out.println("accessJson ： " + accessJson);

        String result2 = new HttpClientUtil().doGet("http://localhost:8081/dmp-service/userToken/checkUser?accessToken=WCf%2FPnKoLGqinyLMJyWO911LLcC36FCkD1OeXh78gRuh66PQ4XRcoz6rtWU8GS0zdG%2F3YZ3aTsrfBBl%2FVJincn1o8aQ7cPwXqhMnsYswozZ8ByuY2W%2F7erXNiP3Jt2ZyTJY2IiP43p831nSmYi%2Fntg8ZoxknURFU%2FVK0YFmlzClTsUzaS0q6KkAcvOQRK%2BHPZPxxxhLjWqrMAQsgDpPhror4p46LS4tQjQldEO1y%2FALRFtTioVvSFe4KRJfI%2BmzpkQGPtgJVCeme4Y%2FO2NY%2BmrGtLR9eUSwANAKdxzFLNYUCZr3mSObN46Ix8x3keaNiuXIJz9BzDRRHHlQN%2FgJVDQ%3D%3D");
        System.out.println("user校验接口返回-写死URL：" + result2);

        String result3 = new HttpClientUtil().doGet("http://localhost:8081/dmp-service/userToken/checkUser?accessToken=" + accessJson);
        System.out.println("创建Token接口返回-参数URL：" + result3);
    }
}
