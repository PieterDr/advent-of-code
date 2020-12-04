package aoc2020;

import util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Day04 implements Day<List<Day04.Passport>> {

    public static void main(String[] args) throws IOException {
        Iterator<String> input = Files.readAllLines(Path.of(Day04.class.getClassLoader().getResource("2020/day04.txt").getPath())).iterator();
        List<Passport> passports = new ArrayList<>();
        StringBuilder claims = new StringBuilder();
        while (input.hasNext()) {
            String line = input.next();
            claims.append(" " + line);
            if (line.isBlank()) {
                passports.add(Passport.of(claims.toString().trim()));
                claims = new StringBuilder();
            }
        }
        passports.add(Passport.of(claims.toString().trim()));
        new Day04().run(passports);
    }

    @Override
    public String part1(List<Passport> input) {
        return "" + input.stream()
                .filter(Passport::containsRequiredClaims)
                .count();
    }

    @Override
    public String part2(List<Passport> input) {
        return "" + input.stream()
                .filter(Passport::isValid)
                .count();
    }

    static class Passport {

        public static final String BYR = "byr";
        public static final String IYR = "iyr";
        public static final String EYR = "eyr";
        public static final String HGT = "hgt";
        public static final String HCL = "hcl";
        public static final String ECL = "ecl";
        public static final String PID = "pid";
        private static final List<String> REQUIRED_CLAIMS = asList(BYR, IYR, EYR, HGT, HCL, ECL, PID);

        private static final List<String> ECL_VALUES = asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        private static final Pattern HCL_PATTERN = Pattern.compile("^#[0-9a-f]{6}$");
        private static final Pattern PID_PATTERN = Pattern.compile("^[0-9]{9}$");

        private Map<String, String> claims;

        private Passport(Map<String, String> claims) {
            this.claims = claims;
        }

        private static Passport of(String claims) {
            return new Passport(Arrays.stream(claims.split(" "))
                    .map(claim -> claim.split(":"))
                    .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1])));
        }

        private boolean containsRequiredClaims() {
            return claims.keySet().containsAll(REQUIRED_CLAIMS);
        }

        private boolean isValid() {
            return containsRequiredClaims()
                    && validByr()
                    && validIyr()
                    && validEyr()
                    && validHgt()
                    && validHcl()
                    && validEcl()
                    && validPid();
        }

        private boolean validByr() {
            var byr = claims.get(BYR);
            return Integer.parseInt(byr) >= 1920 && Integer.parseInt(byr) <= 2002;
        }

        private boolean validIyr() {
            var iyr = Integer.parseInt(claims.get(IYR));
            return iyr >= 2010 && iyr <= 2020;
        }

        private boolean validEyr() {
            var eyr = Integer.parseInt(claims.get(EYR));
            return eyr >= 2020 && eyr <= 2030;
        }

        @SuppressWarnings("DuplicateExpressions")
        private boolean validHgt() {
            var hgt = claims.get(HGT);
            return (hgt.endsWith("cm") && Integer.parseInt(hgt.substring(0, hgt.indexOf("cm"))) >= 150 && Integer.parseInt(hgt.substring(0, hgt.indexOf("cm"))) <= 193)
                    || (hgt.endsWith("in") && Integer.parseInt(hgt.substring(0, hgt.indexOf("in"))) >= 59 && Integer.parseInt(hgt.substring(0, hgt.indexOf("in"))) <= 76);
        }

        private boolean validHcl() {
            var hcl = claims.get(HCL);
            return HCL_PATTERN.matcher(hcl).matches();
        }

        private boolean validEcl() {
            var ecl = claims.get(ECL);
            return ECL_VALUES.contains(ecl);
        }

        private boolean validPid() {
            var pid = claims.get(PID);
            return PID_PATTERN.matcher(pid).matches();
        }
    }
}
