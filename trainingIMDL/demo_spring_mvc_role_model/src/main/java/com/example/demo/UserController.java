package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    /*
     * ## @GetMapping
     *
     * `HTTP GET` に対応するエンドポイントを作成するためのアノテーション
     * `HTTP GET` は、リソースを、**取得** するときに使用するHTTPメソッド
     *
     * - リソースを1件取得する場合、`@PathVariable` でIDを指定する
     * - リソースをN件検索する場合、`@RequestParam` で検索条件を指定する
     * - リソースを全件取得する場合、とくに追加のアノテーションは不要
     */
    @GetMapping("{id}")
    public User get(
            @PathVariable("id") String id
    ) {
        return new User(
                id, "alice@localhost", "Alice"
        );
    }

    /*
     * ## @PostMapping
     *
     * `HTTP POST` に対応するエンドポイントを作成するためのアノテーション
     * `HTTP POST` は、リソースを、**新規作成** するときに使用するHTTPメソッド
     *
     * - 作成内容は `@RequestBody` でHTTPリクエストのContentBodyから受け取る
     */
    @PostMapping
    public User post(
            @RequestBody User user
    ) {
        return user;
    }

    /*
     * ## @PutMapping
     *
     * `HTTP PUT` に対応するエンドポイントを作成するためのアノテーション
     * `HTTP PUT` は、リソースIDで指定されたリソースを、**全部更新** するときに使用するHTTPメソッド
     *
     * - 更新対象となるリソースのIDは `@PathVariable` でURLから受け取り、
     * - 更新内容は `@RequestBody` でHTTPリクエストのContentBodyから受け取る
     */
    @PutMapping("/{id}")
    public User put(
            @PathVariable("id") String id,
            @RequestBody User user
    ) {
        // (1) 検索用のIDと、更新オブジェクト内のIDを比較して一致していない場合はエラーとする（IDは変更不可のため）
        if (!Objects.equals(user.getId(), id)) {
            throw new IllegalArgumentException();
        }

        //// (2) DB等を照会して、IDを元に、既存のリソースを検索する
        // User existedUser = userRepository.findById(id);
        User existedUser = new User(
                "0001", "alice@localhost", "Alice"); // 本来はDB等から取得

        //// (3) 検索結果が存在しなければエラー >> 404エラーで終了
        // if (existedUser == null) {
        //     throw new NullPointerException();
        // }

        //// (4) 検索結果のリソースをRequestBodyの内容に置き換える
        // User updateUser = new User(
        //     existedUser.getId(),
        //     user.getEmail(),
        //     user.getName()
        // );

        //// (5) リソースを更新する
        // User savedUser = userRepository.save(updateUser);

        return user; // 実際には savedUser を返却する
    }

    /*
     * ## @PatchMapping
     *
     * `HTTP PATCH` に対応するエンドポイントを作成するためのアノテーション
     * `HTTP PATCH` は、リソースIDで指定されたリソースを、**部分更新** するときに使用するHTTPメソッド
     *
     * - 更新対象となるリソースのIDは `@PathVariable` でURLから受け取り、
     * - 更新内容は `@RequestBody` でHTTPリクエストのContentBodyから受け取る
     *
     * `HTTP PATCH` にはいくつかの実現方式がある
     * RFCに規定 >> `JSON Patch` / `JSON Merge Patch`
     *
     * ### JSON Patch
     *
     * JSONのフィールドが、`op`, `path`, `value` から構成される
     * あまり直感的に利用できず、ほかのAPI群とも書式が大きく異なるので、**非推奨**
     *
     * - `op` は更新方法の種類を、
     * - `path` は更新するフィールドの名前を、
     * - `value` は更新後の値を、それぞれ設定する
     * - フィールドをそのままにしたい場合にはフィールドそのものを指定しない
     *
     * ```json
     * [
     *   { "op": "replace", "path": "/email", "value": "bob@localhost" },
     *   { "op": "remove" , "path": "/name" }
     * ]
     * ```
     *
     * ### JSON Merge Patch
     *
     * JSONの書式は一般的なものと同じ
     * こちらのほうが直感的に利用できるため、**おすすめ**
     *
     * - フィールドを更新したい場合には値を、
     * - フィールドを削除したい場合には null を、
     * - フィールドをそのままにしたい場合にはフィールドそのものを指定しない
     *
     * ```json
     * {
     *   "email" : "bob@localhost",
     *   "name"  : null
     * }
     * ```
     */
    @PatchMapping("/{id}")
    public User patch(
            @PathVariable("id") String id,
            @RequestBody UserPatchRequest userPatch
    ) {
        //// (1) DB等を照会して、IDを元に、既存のリソースを検索する
        // User existedUser = userRepository.findById(id);
        User existedUser = new User(
                "0001", "alice@localhost", "Alice"); // 本来はDB等から取得

        //// (2) 検索結果が存在しなければエラー >> 404エラーで終了
        // if (existedUser == null) {
        //     throw new NullPointerException();
        // }

        // (3) 検索結果のリソースをRequestBodyの内容に置き換える
        User updateUser = userPatch.merge(existedUser);

        //// (4) リソースを更新する
        // User savedUser = userRepository.save(updateUser);

        return updateUser; // 実際には savedUser を返却する
    }

    /*
     * ## @DeleteMapping
     *
     * `HTTP DELETE` に対応するエンドポイントを作成するためのアノテーション
     * `HTTP DELETE` は、リソースIDで指定されたリソースを、**削除**するときに使用するHTTPメソッド
     *
     * - 削除対象となるリソースのIDは `@PathVariable` でURLから受け取る
     */
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") String id
    ) {
        //// (1) DB等を照会して、IDを元に、既存のリソースを検索する
        // User existedUser = userRepository.findById(id);
        User existedUser = new User(
                "0001", "alice@localhost", "Alice"); // 本来はDB等から取得

        //// (2) 検索結果が存在しなければエラー >> 404エラーで終了
        // if (existedUser == null) {
        //     throw new NullPointerException();
        // }

        //// (3) 受け取ったIDでDBからデータを削除（ないし、論理削除）する
        // userRepository.delete(existedUser.getId());
    }

    //////////////////// 以下、Controller の各種アノテーションのデモ ////////////////////

    /*
     * `@PathVariable` で正規表現を使用する
     */
    @GetMapping("/regex/{param:^[A-Za-z]{4}$}")
    public void getRegex(String alpha) {
        // 正規表現でアルファベット4文字に制限しているため、アルファベット4文字以外の場合はコントローラーに入らない
        System.out.println(alpha);
    }

    /*
     * `@RequestHeader` でリクエストヘッダーの値を取得する
     */
    @GetMapping("/header")
    public void getHeader(
            @RequestHeader(value = "X-Custom-Param", required = false) String xCustomParam,
            @RequestHeader(value = "X-Member-Id") String xMemberId
    ) {
        System.out.println(xCustomParam);
        System.out.println(xMemberId);
    }

    //////////////////// 以下、`@RestControllerAdvice` クラス内の `@ExceptionHandler` のデモ用 ////////////////////

    @GetMapping("/error")
    public void getError() {
        throw new RuntimeException("**RuntimeException**");
    }

    @GetMapping("/errorArgs")
    public void getErrorArgs() {
        throw new IllegalArgumentException("**IllegalArgumentException**");
    }
}
