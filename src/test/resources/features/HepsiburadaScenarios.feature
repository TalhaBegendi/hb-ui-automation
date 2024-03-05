Feature: HepsiburadaCases

  Background:
    Given Click "common.cookieBannerApplyButton" element
    Then Check page url contains "https://www.hepsiburada.com"
    And Wait for given seconds 1
    And Click "search.clickSearchbar" element
    And Fill "search.product" field with "iphone"
    And Click "search.clickSearchButton" element
    Then Check equality of element text "assert.productName" and with text "iphone"
    And Click "product.clickRandomProduct" random product
    And Wait for given seconds 1
    And Keep running codes in new tab
    Then Check elements for Pages

  Scenario: Scenario 1
    When Click "product.reviewsTab" center element
    Then Check with element if there is no reviews
    And Click "reviews.openDropDown" drop down and select "En yeni değerlendirme" with text
    And Click "reviews.clickButton" center element
    Then Check equality of element text "reviews.textThanks" and with text "Teşekkür Ederiz."

  Scenario: Scenario 2
    When Check "productDetail.likeButton" like button for product

  Scenario: Scenario 3
    When Get "productDetail.price" text of field and assign it to variable "productPrice_inProductDetail"
    And Click "product.addToBasket" center element
    And Click "product.goToBasket" center element
    And Get "basket.price" text of field and assign it to variable "productPrice_inBasket"
    Then Check get first variable "productPrice_inProductDetail" and get second variable "productPrice_inBasket"
