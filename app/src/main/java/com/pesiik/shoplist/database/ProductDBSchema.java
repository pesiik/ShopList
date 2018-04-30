package com.pesiik.shoplist.database;

public class ProductDBSchema {
    public static final class ProductTable{
        public static final String NAME = "products";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String PRICE = "price";
            public static final String COUNT = "count";
            public static final String DESCRIPTION = "description";
        }
    }
}
