# Hepsiburada Test Otomasyonu UI

Bu README dosyası, Hepsiburada'nın UI test otomasyonu projesi için gerekli bilgileri içermektedir. Kullanıcıları bilgilendirmek amacıyla aşağıdaki adımlar ve talimatlar sunulmuştur.

## Kurulum

* Proje Java(Intellij)-Selenium üzerinde geliştirilmiştir ve kullanılmak üzere tasarlanmıştır.
* Intellij'den projeyi import etmelisiniz.
* İlgili plugin kurulumları yapılmalıdır. Import ettikten sonra Intellij'de sağ alt tarafta ilgili pop-up da "configure plugin" tıklayıp pluginler yüklenebilir. (Cucumber, lambda v.b)
* SDK seçimi yapılmalıdır. SDK 17 seçilebilir.

## Kullanım

1. **Senaryoları Çalıştırma:**
   * `src/test/resources/features/HepsiburadaScenarios.feature` dosyası açılır.
   * Tüm senaryoları çalıştırmak için, 1. satırdaki "Feature: HepsiburadaCases" yanındaki "run" butonu kullanılabilir.
   * Tek bir senaryoyu çalıştırmak için, örneğin "Scenario: Scenario 1", yanındaki "run" butonu kullanılabilir.
   * "Background" bölümü, tüm senaryolar için ortak stepler içermektedir.
   * Steplerde bulunan " " içindeki değerler ilgili elementi temsil etmektedir.
   * 'src/test/resources/elementValues/HepsiburadaScenarios.json' dosyasından ilgili elementlere değer atanır. Locator tipi belirtilir, key isimlendirmesi yapılır ve steplerde kullanılır.
   * 'src/test/resources/properties/env.properties' dosyasında kullanılacak tarayıcı (chrome veya firefox) ve domain (URL) tanımlanır.
   * Projenin altındaki 'drivers' klasöründe 'chromedriver.exe' ve 'geckodriver.exe' bulunmaktadır. Mac OS üzerinde çalıştırılacaksa, ilgili driverlar bu klasöre eklenir.
   * Daha sonra `src/test/java/utils/driver/DriverFactory.java` dosyasında 25. ve 30. satırlardaki '/drivers/chromedriver.exe' ve '/drivers/geckodriver.exe' ifadelerinden '.exe' kısmı kaldırılır. Ardından proje build edilip çalıştırılabilir.

## Projenin İçeriği

1. **Action.Java Hakkında**
   * `src/test/java/actions/Action.Java` dosyası açılır.
   * Bu dosya, ilgili senaryoların adımlarını içeren methodları barındırır. Aynı zamanda "Helper.Java" ile extend edilmiştir.
   * Action.Java içerisindeki methodlar, `CommonPage.Java` classından çağrılır.

2. **CommonPage.Java Hakkında**
   * `src/test/java/pages/CommonPage.Java` dosyası açılır.
   * Bu dosya, "Action.Java" içerisinde kullanılan methodları içerir. Bu methodlar, bir üst seviyedeki "CommonStep.Java" clasından çağrılmak üzere tanımlanır.
   * Aynı zamanda "Action.Java" ile extend edilmiştir.
   
3. **CommonStep.Java Hakkında**
   * `src/test/java/pages/CommonStep.Java` dosyası açılır.
   * Bu dosya içinde, `@Before`, `@After` ve diğer Cucumber cümleleri bulunmaktadır.
   * CommonPage classından nesne oluşturularak, bu class içerisindeki methodlar kullanılır.
   * CommonStep classındaki Cucumber cümleleri, 'HepsiburadaScenarios.feature' dosyasından çağrılmaktadır.
   
4. **utils.helpers Package Hakkında**
   1. **DataStoreMap Hakkında**
      * Bu paket içinde ilgili methodlar bulunmaktadır.
   2. **elementHelper Package İçerisindeki Classlar Hakkında**
      * HepsiburadaScenarios.json dosyasında kullanılan methodlar ve kodlar bu paket içerisindeki classlarda bulunmaktadır.
   3. **FileHelper.Java Hakkında**
      * Bu dosyadaki methodlar ve kodlar, elementHelper Package içerisindeki ElementMap.Java clasında kullanılmaktadır.
   4. **Helper.Java Hakkında**
      * Tüm yardımcı methodlar bu classta bulunmaktadır.
   5. **PropertyManager.Java Hakkında**
      * Properties içerisindeki alanlar buradan alınmakta ve çağırılmaktadır.

5. **utils.driver Package Hakkında**
   1. **DriverFactory Hakkında**
      * Properties dosyasındaki browser ve domain için gerekli kodlar bu classta bulunmaktadır.
      * Seçilen tarayıcıya göre switch-case yapısı kullanılarak ilgili tarayıcı çalıştırılır.
   2. **WebDrivers Hakkında**
      * DriverFactory sınıfındaki kodlar bu classta kullanılmaktadır.

## Yazar

Talha Beğendi
