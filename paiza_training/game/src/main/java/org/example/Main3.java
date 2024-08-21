package org.example;

import java.util.*;
//
//友達の家で N 人で遊んでいる paiza 君は格闘ゲームを遊ぶことにしました。
//格闘ゲームのルールは次の通りです。
//
//        ・ 各プレイヤーは 決まった hp と 3 種類の技を持っていて、技には強化系の技と攻撃の技があり、各攻撃技には技を出すための発生フレーム F とダメージ A が設定されている。
//
//        ・ hp が 0 になったプレイヤーは退場となる。
//
//        ・あるプレイヤー 1 が、他のプレイヤーにある技 T_1 (発生フレーム F_1 , 攻撃力 A_1) を使って攻撃した場合、攻撃を受けたプレイヤー 2 は反撃の技 T_2 (発生フレーム F_2 , 攻撃力 A_2) を選ぶ。その後、次のようなルールに従っていずれかのプレイヤーにダメージが入る。
//
//
//どちらか片方でもプレイヤーが退場している場合、互いに何も起こらない。
//
//
//強化系の技を使った場合、使ったプレイヤーの他の全ての技の発生フレーム（最短 1 フレーム) を -3 , 攻撃力を +5 する。
//相手が攻撃技を使っていた場合、その攻撃の攻撃力分 hp が減少する。
//
//
//互いに攻撃技を使った場合
//・ F_1 < F_2 のとき、プレイヤー 2 の hp が A_1 減る
//・ F_1 > F_2 のとき、プレイヤー 1 の hp が A_2 減る
//・ F_1 = F_2 のとき、何も起こらない
//
//
//各プレイヤーの持っている技についての情報と、技が出された回数、使われた技の詳細が与えられるので、全ての技が使われた後に場に残っているプレイヤーの人数を答えてください。

//　入力
//　3 6
//　10 1 1 2 2 3 3
//　10 0 0 6 1 7 2
//　10 0 0 7 5 8 3
//　1 1 2 2
//　1 2 3 2
//　1 3 2 3
//　2 2 3 1
//　2 3 3 1
//　1 2 3 2

//  出力
//  2

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int usersNum = scanner.nextInt();
        int eventsNum = scanner.nextInt();
        List<Map<String, Integer>> users = new ArrayList<>();

        for (int i = 0; i < usersNum; i++) {
            Map<String, Integer> user = new HashMap<>();
            user.put("hitPoint", scanner.nextInt());
            user.put("skill1", scanner.nextInt());
            user.put("attackPoint1", scanner.nextInt());
            user.put("skill2", scanner.nextInt());
            user.put("attackPoint2", scanner.nextInt());
            user.put("skill3", scanner.nextInt());
            user.put("attackPoint3", scanner.nextInt());
            users.add(user);
        }

        for (int i = 0; i < eventsNum; i++) {
            int userId1 = scanner.nextInt() - 1;
            String eventName1 = scanner.next();
            String userSkill1 = skill(eventName1);
            int userId2 = scanner.nextInt() - 1;
            String eventName2 = scanner.next();
            String userSkill2 = skill(eventName2);
            assert userSkill1 != null;
            assert userSkill2 != null;
            if (users.get(userId1).get("hitPoint") > 0 && users.get(userId2).get("hitPoint") > 0) {
                if (users.get(userId1).get(userSkill1) == 0) {
                    buff(users, userId1);
                    attack(users, userId2, userId1, userSkill2);
                }
                if (users.get(userId2).get(userSkill2) == 0) {
                    buff(users, userId2);
                    attack(users, userId1, userId2, userSkill1);
                }
                if (users.get(userId1).get(userSkill1) < users.get(userId2).get(userSkill2)) {
                    attack(users, userId1, userId2, userSkill1);
                } else if (users.get(userId2).get(userSkill2) < users.get(userId1).get(userSkill1)) {
                    attack(users, userId2, userId1, userSkill2);
                }
            }
        }

        int count = 0;
        for (Map<String, Integer> user : users) {
            if (user.get("hitPoint") > 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static String skill(String eventName) {
        switch (eventName) {
            case "1" -> {
                return "skill1";
            }
            case "2" -> {
                return "skill2";
            }
            case "3" -> {
                return "skill3";
            }
        }
        return null;
    }

    private static void buff(List<Map<String, Integer>> users, Integer userId) {
        users.get(userId).put("skill1", users.get(userId).get("skill1") - 3);
        users.get(userId).put("attackPoint1", users.get(userId).get("attackPoint1") + 5);
        users.get(userId).put("skill2", users.get(userId).get("skill2") - 3);
        users.get(userId).put("attackPoint2", users.get(userId).get("attackPoint2") + 5);
        users.get(userId).put("skill3", users.get(userId).get("skill3") - 3);
        users.get(userId).put("attackPoint3", users.get(userId).get("attackPoint3") + 5);
        if (users.get(userId).get("skill1") < 1) {
            users.get(userId).put("skill1", 1);
        } else if (users.get(userId).get("skill2") < 1) {
            users.get(userId).put("skill2", 1);
        } else if (users.get(userId).get("skill3") < 1) {
            users.get(userId).put("skill3", 1);
        }
    }

    private static void attack(List<Map<String, Integer>> users, Integer attackUser, Integer defenseUser, String skill) {
        switch (skill) {
            case "skill1" ->
                    users.get(defenseUser).put("hitPoint", users.get(defenseUser).get("hitPoint") - users.get(attackUser).get("attackPoint1"));
            case "skill2" ->
                    users.get(defenseUser).put("hitPoint", users.get(defenseUser).get("hitPoint") - users.get(attackUser).get("attackPoint2"));
            case "skill3" ->
                    users.get(defenseUser).put("hitPoint", users.get(defenseUser).get("hitPoint") - users.get(attackUser).get("attackPoint3"));
        }
    }
}