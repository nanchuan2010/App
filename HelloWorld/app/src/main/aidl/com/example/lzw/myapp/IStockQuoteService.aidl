// IStockQuoteService.aidl
package com.example.lzw.myapp;

// Declare any non-default types here with import statements

interface IStockQuoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    double getQuote(in String ticker);
}
