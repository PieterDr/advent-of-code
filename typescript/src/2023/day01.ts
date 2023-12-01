import {getInput} from "./util";

const example = [
  "two1nine",
  "eightwothree",
  "abcone2threexyz",
  "xtwone3four",
  "4nineeightseven2",
  "zoneight234",
  "7pqrstsixteen",
]
const input = getInput("day01");

part1(input)
part2(input);

function part1(input: string[]) {
  let result = input.map((line) => line.replaceAll(/[a-z]/g, ""))
    .map((line) => line.charAt(0) + line.charAt(line.length - 1))
    .map(line => Number.parseInt(line))
    .reduce((a, b) => a + b, 0);

  console.log(result)
}

function part2(input: string[]) {
  part1(
    input.map(line => line
      .replaceAll(/oneight/g, "18")
      .replaceAll(/twone/g, "21")
      .replaceAll(/threeight/g, "38")
      .replaceAll(/fiveight/g, "58")
      .replaceAll(/sevenine/g, "79")
      .replaceAll(/eightwo/g, "82")
      .replaceAll(/eighthree/g, "83")
      .replaceAll(/nineight/g, "98")
      .replaceAll(/one/g, "1")
      .replaceAll(/two/g, "2")
      .replaceAll(/three/g, "3")
      .replaceAll(/four/g, "4")
      .replaceAll(/five/g, "5")
      .replaceAll(/six/g, "6")
      .replaceAll(/seven/g, "7")
      .replaceAll(/eight/g, "8")
      .replaceAll(/nine/g, "9")
    )
  );
}
