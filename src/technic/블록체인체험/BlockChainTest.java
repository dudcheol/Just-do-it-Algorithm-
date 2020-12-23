package technic.블록체인체험;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BlockChainTest {
    private static class Block{
        int nonce;
        String data;
        String prevhash;
        String hash;

        public Block(int nonce, String data, String prevhash, String hash) {
            this.nonce = nonce;
            this.data = data;
            this.prevhash = prevhash;
            this.hash = hash;
        }

        @Override
        public String toString() {
            return "nonce:" + nonce + "\n" +
                    "data:'" + data + '\'' + "\n" +
                    "prevhash:'" + prevhash + '\'' + "\n" +
                    "hash:'" + hash + '\'' + "\n";
        }
    }

    public static void main(String[] args) throws Exception {
        Block genesis = new Block(0, "Genesis Block", "", getSHA256byNonce(0));
        System.out.println(genesis);

        int limit = 9;
        int cnt = 0;
        int nonce = genesis.nonce;
        String prevhash = genesis.hash;
        String[] datas = {"2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"};
        while(cnt < limit){
            // 블록 생성하면서 해시값 앞이 00000인 경우만 통과
            Block block = null;
            while (true){
                block = new Block(++nonce, datas[cnt], prevhash, getSHA256byNonce(nonce));
                if (block.hash.startsWith("00000")) break;
            }
            // 블록이 생성되면 다음 블록을 위한 작업실시
            cnt++;
            prevhash = block.hash;
            System.out.println(block);
        }

        System.out.println("...End...");
    }

    public static String getSHA256byNonce(int nonce) throws Exception {
        return bytesToHex1(sha256(Integer.toString(nonce)));
    }

    /**
     * SHA-256으로 해싱하는 메소드
     *
     * @param String
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());

        return md.digest();
    }

    /**
     * 바이트를 헥사값으로 변환한다, type 1
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex1(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
