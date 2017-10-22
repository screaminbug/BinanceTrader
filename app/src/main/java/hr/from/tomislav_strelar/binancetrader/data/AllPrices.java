package hr.from.tomislav_strelar.binancetrader.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Tomislav on 15.10.2017..
 */

public class AllPrices {
    private Map<AllPrices.Symbol, BigDecimal> prices = new TreeMap<>();

    public static class Symbol {
        private String mSym;
        public Symbol(String sym) {
            mSym = sym.toLowerCase();
        }

        @Override
        public String toString() { return mSym; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Symbol symbol = (Symbol) o;

            return mSym != null ? mSym.equals(symbol.mSym) : symbol.mSym == null;

        }

        @Override
        public int hashCode() {
            return mSym != null ? mSym.hashCode() : 0;
        }
    }

    public AllPrices() {
        // just for testing purposes
        prices.put(new AllPrices.Symbol("ETHBTC"), new BigDecimal("0.00345644"));
    }

    AllPrices.Symbol[] getSymbols() {
        return prices.keySet().toArray(new AllPrices.Symbol[prices.keySet().size()]);
    }

    BigDecimal getPrice(AllPrices.Symbol symbol) {
        return prices.get(symbol);
    }
}
