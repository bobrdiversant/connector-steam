package ru.dmm.steamservice.service;

/**
 * Created by Dmitry
 */
public enum CurrencyEnum {
    US_DOLLAR(1),
    BRITISH_POUND(2),
    EURO(3),
    SWISS_FRANC(4),
    RUSSIAN_RUBLE(5),
    POLISH_ZLOTY(6),
    BRAZILIAN_REAL(7),
    JAPANESE_YEN(8),
    NORWEGIAN_KRONE(9),
    INDONESIAN_RUPIAH(10),
    MALAYSIAN_RINGGIT(11),
    PHILIPPINE_PESO(12),
    SINGAPORE_DOLLAR(13),
    THAI_BAHT(14),
    VIETNAMESE_DONG(15),
    SOUTH_KOREAN_WON(16),
    TURKISH_LIRA(17),
    UKRAINE_HRYVNIA(18),
    MEXICAN_PESO(19),
    CANADIAN_DOLLAR(20),
    AUSTRAILIAN_DOLLAR(21),
    NEW_ZEALAND_DOLLAR(22),
    CHINESE_YUAN(23),
    INDIAN_RUPEE(24),
    CHILEAN_PESO(25),
    PERUVIAN_NUEVO_SOL(26),
    COLOMBIAN_PESO(27),
    SOUTH_AFRICAN_RAND(28),
    HONG_KONG_DOLLAR(29),
    TAIWAN_DOLLAR(30),
    SAUDI_RIYAL(31),
    UAE_DIRHAM(32);
//    CIS_US_DOLLAR("CIS - U.S. Dollar", "az", 1),

    private int codeCurrency;

    CurrencyEnum(int codeCurrency) {
        this.codeCurrency = codeCurrency;
    }

    public int getCodeCurrency() {
        return codeCurrency;
    }

}
