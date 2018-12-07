package aoc2017;

import static java.util.Arrays.stream;

public class Day05 {

    public static void main(String[] args) {
        String input = "1\n" +
                "2\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "2\n" +
                "-2\n" +
                "-2\n" +
                "-3\n" +
                "-3\n" +
                "-7\n" +
                "0\n" +
                "-1\n" +
                "0\n" +
                "-10\n" +
                "-8\n" +
                "-12\n" +
                "-8\n" +
                "-3\n" +
                "-6\n" +
                "0\n" +
                "0\n" +
                "-18\n" +
                "-17\n" +
                "-11\n" +
                "-18\n" +
                "1\n" +
                "-7\n" +
                "-10\n" +
                "-4\n" +
                "-18\n" +
                "-8\n" +
                "-26\n" +
                "-15\n" +
                "-24\n" +
                "0\n" +
                "-8\n" +
                "-17\n" +
                "-15\n" +
                "-24\n" +
                "-7\n" +
                "-21\n" +
                "-15\n" +
                "-19\n" +
                "-30\n" +
                "-40\n" +
                "-44\n" +
                "-23\n" +
                "-3\n" +
                "-25\n" +
                "2\n" +
                "-31\n" +
                "-20\n" +
                "-45\n" +
                "-51\n" +
                "-50\n" +
                "-34\n" +
                "-4\n" +
                "-33\n" +
                "-41\n" +
                "0\n" +
                "-49\n" +
                "-43\n" +
                "0\n" +
                "-62\n" +
                "1\n" +
                "1\n" +
                "-32\n" +
                "-50\n" +
                "-22\n" +
                "-10\n" +
                "-60\n" +
                "-13\n" +
                "-46\n" +
                "-57\n" +
                "-40\n" +
                "-28\n" +
                "-33\n" +
                "-34\n" +
                "-13\n" +
                "-40\n" +
                "-5\n" +
                "-49\n" +
                "-17\n" +
                "-25\n" +
                "-71\n" +
                "-5\n" +
                "-16\n" +
                "-23\n" +
                "-58\n" +
                "-69\n" +
                "-22\n" +
                "-28\n" +
                "-84\n" +
                "-70\n" +
                "-71\n" +
                "-73\n" +
                "-87\n" +
                "-8\n" +
                "-11\n" +
                "-99\n" +
                "-65\n" +
                "-27\n" +
                "-32\n" +
                "-48\n" +
                "-87\n" +
                "-96\n" +
                "1\n" +
                "-58\n" +
                "-101\n" +
                "-94\n" +
                "2\n" +
                "-52\n" +
                "-34\n" +
                "-22\n" +
                "2\n" +
                "-25\n" +
                "-7\n" +
                "-36\n" +
                "-66\n" +
                "-84\n" +
                "-100\n" +
                "-45\n" +
                "-65\n" +
                "-69\n" +
                "-52\n" +
                "-5\n" +
                "-4\n" +
                "-93\n" +
                "-100\n" +
                "-7\n" +
                "-13\n" +
                "-50\n" +
                "-87\n" +
                "-87\n" +
                "-4\n" +
                "-119\n" +
                "-25\n" +
                "1\n" +
                "-41\n" +
                "-58\n" +
                "-24\n" +
                "-12\n" +
                "-15\n" +
                "-39\n" +
                "-140\n" +
                "-40\n" +
                "-136\n" +
                "-88\n" +
                "-141\n" +
                "-112\n" +
                "-43\n" +
                "-68\n" +
                "-67\n" +
                "-128\n" +
                "-23\n" +
                "-24\n" +
                "-90\n" +
                "-56\n" +
                "-71\n" +
                "-146\n" +
                "-46\n" +
                "-41\n" +
                "-76\n" +
                "-54\n" +
                "-38\n" +
                "-144\n" +
                "-53\n" +
                "-1\n" +
                "-97\n" +
                "0\n" +
                "0\n" +
                "-70\n" +
                "-60\n" +
                "-171\n" +
                "-12\n" +
                "-97\n" +
                "-147\n" +
                "-21\n" +
                "-174\n" +
                "-108\n" +
                "-101\n" +
                "-91\n" +
                "-56\n" +
                "-76\n" +
                "-13\n" +
                "-177\n" +
                "-1\n" +
                "-40\n" +
                "-2\n" +
                "-177\n" +
                "-136\n" +
                "-168\n" +
                "-126\n" +
                "-5\n" +
                "-175\n" +
                "-177\n" +
                "-144\n" +
                "-104\n" +
                "-174\n" +
                "-4\n" +
                "-172\n" +
                "-114\n" +
                "-69\n" +
                "-18\n" +
                "-24\n" +
                "-157\n" +
                "-47\n" +
                "-110\n" +
                "-6\n" +
                "-155\n" +
                "-79\n" +
                "-157\n" +
                "-93\n" +
                "-93\n" +
                "-114\n" +
                "-77\n" +
                "-148\n" +
                "-32\n" +
                "-100\n" +
                "-192\n" +
                "-144\n" +
                "-123\n" +
                "-192\n" +
                "-65\n" +
                "-220\n" +
                "-143\n" +
                "-1\n" +
                "-40\n" +
                "-149\n" +
                "-69\n" +
                "-230\n" +
                "-202\n" +
                "-69\n" +
                "-52\n" +
                "-112\n" +
                "-175\n" +
                "-72\n" +
                "-69\n" +
                "-168\n" +
                "-198\n" +
                "-181\n" +
                "-156\n" +
                "-37\n" +
                "-179\n" +
                "-168\n" +
                "-81\n" +
                "-51\n" +
                "-205\n" +
                "-2\n" +
                "-104\n" +
                "-25\n" +
                "-199\n" +
                "-83\n" +
                "-176\n" +
                "-115\n" +
                "-174\n" +
                "-204\n" +
                "-207\n" +
                "-127\n" +
                "-161\n" +
                "-219\n" +
                "-27\n" +
                "-107\n" +
                "-160\n" +
                "-141\n" +
                "-34\n" +
                "-39\n" +
                "-182\n" +
                "-46\n" +
                "-41\n" +
                "-90\n" +
                "-267\n" +
                "-234\n" +
                "-48\n" +
                "-72\n" +
                "-49\n" +
                "-110\n" +
                "-266\n" +
                "-167\n" +
                "-194\n" +
                "-124\n" +
                "-183\n" +
                "-184\n" +
                "-108\n" +
                "-49\n" +
                "-130\n" +
                "-166\n" +
                "-198\n" +
                "-87\n" +
                "-196\n" +
                "-183\n" +
                "-275\n" +
                "-130\n" +
                "-275\n" +
                "-39\n" +
                "-70\n" +
                "-143\n" +
                "-257\n" +
                "-22\n" +
                "-53\n" +
                "-12\n" +
                "-93\n" +
                "-30\n" +
                "-141\n" +
                "-32\n" +
                "-239\n" +
                "-284\n" +
                "-186\n" +
                "-211\n" +
                "-160\n" +
                "-145\n" +
                "-21\n" +
                "-167\n" +
                "-258\n" +
                "-67\n" +
                "-56\n" +
                "-262\n" +
                "-272\n" +
                "-19\n" +
                "-194\n" +
                "-244\n" +
                "-95\n" +
                "-261\n" +
                "-27\n" +
                "-109\n" +
                "-271\n" +
                "-7\n" +
                "-146\n" +
                "-328\n" +
                "-266\n" +
                "-207\n" +
                "-76\n" +
                "-204\n" +
                "-273\n" +
                "-177\n" +
                "-159\n" +
                "-68\n" +
                "-231\n" +
                "-215\n" +
                "-75\n" +
                "-264\n" +
                "-27\n" +
                "-342\n" +
                "-298\n" +
                "-338\n" +
                "-178\n" +
                "-268\n" +
                "-303\n" +
                "-78\n" +
                "-283\n" +
                "-149\n" +
                "-147\n" +
                "-209\n" +
                "-146\n" +
                "-147\n" +
                "-282\n" +
                "-131\n" +
                "-204\n" +
                "-129\n" +
                "-319\n" +
                "-166\n" +
                "-256\n" +
                "-114\n" +
                "-267\n" +
                "-211\n" +
                "-15\n" +
                "-194\n" +
                "-53\n" +
                "-114\n" +
                "-236\n" +
                "-127\n" +
                "-33\n" +
                "-316\n" +
                "-123\n" +
                "-180\n" +
                "-234\n" +
                "-131\n" +
                "-107\n" +
                "-21\n" +
                "-209\n" +
                "-174\n" +
                "-174\n" +
                "-24\n" +
                "-1\n" +
                "-281\n" +
                "-367\n" +
                "-251\n" +
                "-42\n" +
                "-17\n" +
                "-158\n" +
                "-33\n" +
                "-181\n" +
                "-26\n" +
                "-286\n" +
                "-235\n" +
                "-262\n" +
                "-1\n" +
                "-374\n" +
                "-121\n" +
                "-253\n" +
                "-215\n" +
                "-19\n" +
                "-114\n" +
                "-8\n" +
                "-372\n" +
                "-89\n" +
                "-185\n" +
                "-50\n" +
                "-71\n" +
                "-130\n" +
                "-248\n" +
                "-274\n" +
                "-9\n" +
                "-99\n" +
                "-66\n" +
                "-402\n" +
                "-256\n" +
                "-294\n" +
                "-313\n" +
                "-256\n" +
                "-36\n" +
                "-229\n" +
                "-64\n" +
                "-47\n" +
                "-32\n" +
                "-259\n" +
                "-178\n" +
                "-278\n" +
                "-416\n" +
                "-334\n" +
                "-286\n" +
                "-229\n" +
                "-377\n" +
                "-50\n" +
                "-424\n" +
                "-359\n" +
                "-182\n" +
                "-41\n" +
                "-270\n" +
                "-96\n" +
                "-372\n" +
                "-1\n" +
                "-100\n" +
                "-45\n" +
                "-88\n" +
                "-111\n" +
                "-373\n" +
                "-247\n" +
                "-185\n" +
                "-356\n" +
                "-66\n" +
                "-246\n" +
                "-157\n" +
                "-326\n" +
                "-196\n" +
                "-258\n" +
                "-325\n" +
                "-218\n" +
                "-43\n" +
                "-224\n" +
                "-429\n" +
                "-461\n" +
                "-159\n" +
                "-101\n" +
                "-21\n" +
                "-187\n" +
                "-416\n" +
                "-154\n" +
                "-416\n" +
                "-311\n" +
                "-54\n" +
                "-189\n" +
                "-379\n" +
                "-375\n" +
                "-300\n" +
                "0\n" +
                "-405\n" +
                "-170\n" +
                "-478\n" +
                "-119\n" +
                "-451\n" +
                "-121\n" +
                "-30\n" +
                "-304\n" +
                "-320\n" +
                "-218\n" +
                "-369\n" +
                "-294\n" +
                "-41\n" +
                "-328\n" +
                "-482\n" +
                "-42\n" +
                "-200\n" +
                "-254\n" +
                "-409\n" +
                "-260\n" +
                "-447\n" +
                "-196\n" +
                "-156\n" +
                "-394\n" +
                "-142\n" +
                "-180\n" +
                "-376\n" +
                "-316\n" +
                "-323\n" +
                "-455\n" +
                "-262\n" +
                "-360\n" +
                "-143\n" +
                "-318\n" +
                "-242\n" +
                "-226\n" +
                "-382\n" +
                "-211\n" +
                "-493\n" +
                "-443\n" +
                "-14\n" +
                "-231\n" +
                "-304\n" +
                "-168\n" +
                "-396\n" +
                "-100\n" +
                "-201\n" +
                "-187\n" +
                "-209\n" +
                "-49\n" +
                "-45\n" +
                "-150\n" +
                "-309\n" +
                "-275\n" +
                "-449\n" +
                "-523\n" +
                "-260\n" +
                "-366\n" +
                "-222\n" +
                "-483\n" +
                "-234\n" +
                "-209\n" +
                "-398\n" +
                "-247\n" +
                "-343\n" +
                "-266\n" +
                "-456\n" +
                "-396\n" +
                "-528\n" +
                "-232\n" +
                "-241\n" +
                "-482\n" +
                "-417\n" +
                "-362\n" +
                "-406\n" +
                "-503\n" +
                "-164\n" +
                "-146\n" +
                "-198\n" +
                "-285\n" +
                "-23\n" +
                "-133\n" +
                "-506\n" +
                "-159\n" +
                "-203\n" +
                "-70\n" +
                "-35\n" +
                "-410\n" +
                "-209\n" +
                "-482\n" +
                "-304\n" +
                "-45\n" +
                "-550\n" +
                "-27\n" +
                "-6\n" +
                "-117\n" +
                "-121\n" +
                "-143\n" +
                "-265\n" +
                "-196\n" +
                "-179\n" +
                "-334\n" +
                "-77\n" +
                "-253\n" +
                "-526\n" +
                "-276\n" +
                "-437\n" +
                "-212\n" +
                "-276\n" +
                "-253\n" +
                "-414\n" +
                "-192\n" +
                "-48\n" +
                "-53\n" +
                "-28\n" +
                "-24\n" +
                "-139\n" +
                "-554\n" +
                "-534\n" +
                "-82\n" +
                "-35\n" +
                "-237\n" +
                "-447\n" +
                "-274\n" +
                "-125\n" +
                "-377\n" +
                "-404\n" +
                "-101\n" +
                "-241\n" +
                "-166\n" +
                "-192\n" +
                "-120\n" +
                "-469\n" +
                "-461\n" +
                "-534\n" +
                "-222\n" +
                "-409\n" +
                "-363\n" +
                "-211\n" +
                "-477\n" +
                "-300\n" +
                "-491\n" +
                "-506\n" +
                "-512\n" +
                "-344\n" +
                "-554\n" +
                "-31\n" +
                "-617\n" +
                "-49\n" +
                "-268\n" +
                "-63\n" +
                "-143\n" +
                "-512\n" +
                "-115\n" +
                "-299\n" +
                "-344\n" +
                "-453\n" +
                "-573\n" +
                "-590\n" +
                "-330\n" +
                "-342\n" +
                "-347\n" +
                "-3\n" +
                "-46\n" +
                "-344\n" +
                "-423\n" +
                "-561\n" +
                "-411\n" +
                "-95\n" +
                "-9\n" +
                "-323\n" +
                "-354\n" +
                "-523\n" +
                "-523\n" +
                "-526\n" +
                "-573\n" +
                "-162\n" +
                "-281\n" +
                "-578\n" +
                "-326\n" +
                "-475\n" +
                "-506\n" +
                "-319\n" +
                "-190\n" +
                "-15\n" +
                "-193\n" +
                "-586\n" +
                "-430\n" +
                "-166\n" +
                "-72\n" +
                "-160\n" +
                "-530\n" +
                "-233\n" +
                "-626\n" +
                "-345\n" +
                "-253\n" +
                "-413\n" +
                "-152\n" +
                "-217\n" +
                "-672\n" +
                "-675\n" +
                "-498\n" +
                "-417\n" +
                "-104\n" +
                "-25\n" +
                "-114\n" +
                "-283\n" +
                "-187\n" +
                "-314\n" +
                "-51\n" +
                "-88\n" +
                "-217\n" +
                "-311\n" +
                "-408\n" +
                "-148\n" +
                "-420\n" +
                "-615\n" +
                "-221\n" +
                "-454\n" +
                "-461\n" +
                "-213\n" +
                "-131\n" +
                "-115\n" +
                "-326\n" +
                "-244\n" +
                "-561\n" +
                "-133\n" +
                "-50\n" +
                "-434\n" +
                "-544\n" +
                "-616\n" +
                "-418\n" +
                "-302\n" +
                "-366\n" +
                "-202\n" +
                "-624\n" +
                "-302\n" +
                "-241\n" +
                "-158\n" +
                "-494\n" +
                "-321\n" +
                "-555\n" +
                "-136\n" +
                "-601\n" +
                "-363\n" +
                "-246\n" +
                "-630\n" +
                "-705\n" +
                "-121\n" +
                "-427\n" +
                "-91\n" +
                "-453\n" +
                "-664\n" +
                "-271\n" +
                "-210\n" +
                "-155\n" +
                "-573\n" +
                "-232\n" +
                "-349\n" +
                "-152\n" +
                "-208\n" +
                "-233\n" +
                "-395\n" +
                "-185\n" +
                "-121\n" +
                "-291\n" +
                "-149\n" +
                "-11\n" +
                "-263\n" +
                "-55\n" +
                "-488\n" +
                "-78\n" +
                "-155\n" +
                "-447\n" +
                "-667\n" +
                "-556\n" +
                "-730\n" +
                "-406\n" +
                "-454\n" +
                "-382\n" +
                "-496\n" +
                "-568\n" +
                "-235\n" +
                "-374\n" +
                "-200\n" +
                "-475\n" +
                "-513\n" +
                "-275\n" +
                "-423\n" +
                "-95\n" +
                "-643\n" +
                "-434\n" +
                "-144\n" +
                "-527\n" +
                "-418\n" +
                "-577\n" +
                "-564\n" +
                "-211\n" +
                "-485\n" +
                "-122\n" +
                "-387\n" +
                "-484\n" +
                "-212\n" +
                "-129\n" +
                "-763\n" +
                "-268\n" +
                "-83\n" +
                "-428\n" +
                "-518\n" +
                "-394\n" +
                "-784\n" +
                "-223\n" +
                "-549\n" +
                "-232\n" +
                "-175\n" +
                "-566\n" +
                "-569\n" +
                "-329\n" +
                "-300\n" +
                "-3\n" +
                "-563\n" +
                "-571\n" +
                "-369\n" +
                "-753\n" +
                "-111\n" +
                "-609\n" +
                "-461\n" +
                "-514\n" +
                "-514\n" +
                "-174\n" +
                "-800\n" +
                "-702\n" +
                "-808\n" +
                "-780\n" +
                "-708\n" +
                "-774\n" +
                "-811\n" +
                "-518\n" +
                "-741\n" +
                "-404\n" +
                "-364\n" +
                "-48\n" +
                "-74\n" +
                "-228\n" +
                "-333\n" +
                "-380\n" +
                "-90\n" +
                "-813\n" +
                "-457\n" +
                "-275\n" +
                "-414\n" +
                "-281\n" +
                "-723\n" +
                "-101\n" +
                "-123\n" +
                "-438\n" +
                "-657\n" +
                "-406\n" +
                "-779\n" +
                "-161\n" +
                "-825\n" +
                "-715\n" +
                "-79\n" +
                "-358\n" +
                "-183\n" +
                "-554\n" +
                "-716\n" +
                "-596\n" +
                "-56\n" +
                "-39\n" +
                "-505\n" +
                "-268\n" +
                "-425\n" +
                "-609\n" +
                "-69\n" +
                "-92\n" +
                "-7\n" +
                "-44\n" +
                "-10\n" +
                "-681\n" +
                "-86\n" +
                "-30\n" +
                "-225\n" +
                "-551\n" +
                "-213\n" +
                "-335\n" +
                "-829\n" +
                "-817\n" +
                "-804\n" +
                "-74\n" +
                "-109\n" +
                "-732\n" +
                "-781\n" +
                "-401\n" +
                "-370\n" +
                "-40\n" +
                "-526\n" +
                "-694\n" +
                "-336\n" +
                "-859\n" +
                "-294\n" +
                "-682\n" +
                "-264\n" +
                "-325\n" +
                "-27\n" +
                "-679\n" +
                "-135\n" +
                "-82\n" +
                "-711\n" +
                "-570\n" +
                "-159\n" +
                "-179\n" +
                "-604\n" +
                "-41\n" +
                "-580\n" +
                "-403\n" +
                "-540\n" +
                "-262\n" +
                "-69\n" +
                "-329\n" +
                "-5\n" +
                "-879\n" +
                "-345\n" +
                "-632\n" +
                "-367\n" +
                "-183\n" +
                "-38\n" +
                "-80\n" +
                "-63\n" +
                "-448\n" +
                "-832\n" +
                "-656\n" +
                "-774\n" +
                "-292\n" +
                "-474\n" +
                "-596\n" +
                "-862\n" +
                "-842\n" +
                "-16\n" +
                "-107\n" +
                "-243\n" +
                "-647\n" +
                "-782\n" +
                "-336\n" +
                "-785\n" +
                "-816\n" +
                "-78\n" +
                "-416\n" +
                "-712\n" +
                "-810\n" +
                "-389\n" +
                "-264\n" +
                "-304\n" +
                "-847\n" +
                "-520\n" +
                "-619\n" +
                "-161\n" +
                "1\n" +
                "-584\n" +
                "-719\n" +
                "-486\n" +
                "-516\n" +
                "-360\n" +
                "-747\n" +
                "-363\n" +
                "-672\n" +
                "-134\n" +
                "-177\n" +
                "-894\n" +
                "-778\n" +
                "-582\n" +
                "-766\n" +
                "-769\n" +
                "-114\n" +
                "-843\n" +
                "-287\n" +
                "-493\n" +
                "-30\n" +
                "-620\n" +
                "-467\n" +
                "-141\n" +
                "-206\n" +
                "-437\n" +
                "-367\n" +
                "-426\n" +
                "-799\n" +
                "-943\n" +
                "-508\n" +
                "-594\n" +
                "-545\n" +
                "-61\n" +
                "-829\n" +
                "-844\n" +
                "-589\n" +
                "-775\n" +
                "-409\n" +
                "-28\n" +
                "-726\n" +
                "-452\n" +
                "-261\n" +
                "-613\n" +
                "-106\n" +
                "-441\n" +
                "-496\n" +
                "-643\n" +
                "-606\n" +
                "-742\n" +
                "-408\n" +
                "-321\n" +
                "-260\n" +
                "-333\n" +
                "-328\n" +
                "-751\n" +
                "-21\n" +
                "-768\n" +
                "-36\n" +
                "-265\n" +
                "-936\n" +
                "-697\n" +
                "-702\n" +
                "-924\n" +
                "-89\n" +
                "-215\n" +
                "-896\n" +
                "-216\n" +
                "-477";
        int[] list = toIntArray(input);
        int i = 0;
        int steps = 0;
        while (true) {
            try {
                int j = i;
                int offset = list[i];
                i += offset;
                if (offset >= 3) {
                    list[j] -= 1;
                } else {
                    list[j]++;
                }

            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(steps);
                throw ex;
            }
            steps++;
        }
    }

    private static int[] toIntArray(String input) {
        return stream(input.split("\n"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}