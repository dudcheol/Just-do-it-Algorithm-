function cube(x) {
  return x * x;
}

function hello(str) {
  return 'hello!' + str;
}

var tommy = {
  name: 'tommy lee',
  age: 12,
  address: 'london',
};
const foo = Math.PI * 2;

//객체
export { cube, foo, hello, tommy };
// export default function cube2(x) {
//     return x + 1;
// }

export default {
  name: 'jane lee',
  age: 32,
  address: 'la',
};
