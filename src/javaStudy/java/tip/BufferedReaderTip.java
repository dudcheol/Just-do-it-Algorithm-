package javaStudy.java.tip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class BufferedReaderTip {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader(src));
        // StringReader로 너무 긴 인풋값 일일이 복.붙할 필요 없도록 할 수 있다.
    }

    static String src = "182\n" +
            "(({<(({{[[[[<<[[(<[[{([{{{[<[[[{<<(<[[{}[]{}{}[]]]><><>{})[]{}><>[]<>><>}][]]<>{}]>]()}()()(){}}}{}][])(){}<>()}]{}[]]>()[][][]){}]]{}[]<>><>{}[]{}<>>]]]][]{}{}[]()}}))>}<>{}()))[][]\n" +
            "298\n" +
            "{({{[({([{(<[([(([<({[{{[[({{[({([<{(<[[(<((<[{[<[([((<{{[([{<<[{(<({[<{}()>[]<>][]})>[])<>()[]}]>><>(){}()[]}]{}()<>[]<>)<>{}<>{}{}]}()}<>>)[]){}])]>[][]{}]()}][]()>[]))[]>)]][]>)[]{}}[]<><>>]()[])}{}){}]}}<><>){}][]{}{}]<>[]}(){}<>}][]})[]()><>]))])]>{}{}())}{}])<>}{})]{}{}}[])())<>{}[]<><>}[]{}\n" +
            "230\n" +
            "[({([<[<[<<(([{([<((({<{([{[<[<<<([<[<([[{([<[{({([<(<[[][]]<>{}<>[]>){}{}<>>]{}())})}[]]{}>[][][]]()[][]<><>()<>){}}]])[][][]>]>()](){}[])>>><><>{}]>]{}[]}<>][]())}[][]>}<>)()))>{}[]()])[][]}]<>()<>))>>()][]>][][]>]())}<><>{}<>)]\n" +
            "226\n" +
            "[[[{{[[<[([((([({{[{<<([[[([[{<[{{<{[<[([<<<((([{(<({<([(<><>){}])()[]>}<>())>){}}]))){}>()<><>>>[]]<>)]>]{}()}(){}>()<><>}}]()>}<>{}]])<>{}]()(){}][]()])[]{}(){}<>>[]>}{}{}](){}}})()])(){}<>))][])[]()<>]<><>>]()][]{}}}[]]]]()\n" +
            "242\n" +
            "((([<[{{<[<{<{({(({({({<{{{((([{[({[{<{{(([[{([{<<({<[]>}){}[]()()>>}[][]()][]<>){}<>}]]))}[]}()><>[]}(){}]()[][][]})]<>}{}<>](){}){}){})<><><>{}<>[]}}<>{}{}}{}[]>{}}(){}){}<>})}))[]})()}>[]{}{}()}>]>{}[][][][]}}][][][]{}<>>][])()[]))()()[]{}\n" +
            "138\n" +
            "{[<(({[{(({[(({{{]{<[([[({[[[[<>]]]{}]{}})]]()<>{}{}<>()<>)()<>{}[]<>]>{}})<>()<><><>}{}}}())){}]}))}()<>()[]{}]})<><><><>)>[][][]]}<>[]{}\n" +
            "238\n" +
            "{<[[({[([{{(({{((<({(<{{([<{<([((<<(<{[<<([{{[<[{<{{{[<[{<{[[]]}()[][]}()}]>]}{}{}}<>[]<>{}(){}}>}]{}{}><>[]]}[]{}()[]{}}]{}())()<><>()>>]()<>}>{}[])<>()>>()))()])>{}}>])}{}{}}>)()<>[]}){}>)<>)()}}())){}<>()}[][]}]){}<>[]<>]}[])]()]()>}<>\n" +
            "272\n" +
            "([{[<[[([([{[[([[({([(<[[[<{(({<([{([{<{[<<((({(<{<[({<{<<([[{<[[{{[<(([{}])<>)[]>]()<>}<>}[]]]{}()>}][]]{})<>{}[]>>}>{}<>}{})()]<>><>}[][]>)<>{}}[]<>))){}[]>>[]]}[]>}][])[]}]())>[]{}{}}[]{}))}>(){}[]{}]<>{}]]()>)<><><><>[]])}{})[]()]])]]}])<>][]{}<>)()]<><>()]>]<>}<>()])\n" +
            "218\n" +
            "[{({[<{[<{[[[<[{[[[[{([{[<[[<<[{{((<[([[<[(<[({}[]<>[]()[]()(){}){})<>()>[][]<>[])<><>[](){}[]{}<>]><>]])]>[]){}<>)[][]}}<><>()<>]<>><>{}>[]<>]{}]>]<>}]{}())[]}[]]<>]]]}]()>]<>(){}]()<>()]()}>{}<>]{}}>]()[]()[]})()}]()\n" +
            "258\n" +
            "({{[({<{<<{{([[<{({{{[[({[<(<{<<{<<{{{{([{[{<{{[<<[[[<[{<(<[({(<>)})]><>[])()>{}}[]]{}<>><>]]]<>>{}>]}{}[]}>}]<>[]}]){}<>{}}[]}}}{}<>>>}>[]><><>()<>[]}><>)><><><>]}){}]]<>[]<>}[]<>{}}}()<>)}<>{}[]>][]{}])<>[][]{}}()}>[]<>>}<><>>()}{}<><>[]()())<>]}{}{}[]}[])\n";
}