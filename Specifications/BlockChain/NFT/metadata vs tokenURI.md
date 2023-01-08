# metadata vs tokenuri
简答地说，metadata 是一个Opensea 约定的数据表现形式；tokenURI 是指向这个 metadata 的链接。

## opensea metadata standard
https://docs.opensea.io/docs/metadata-standards
Here's an example of metadata for one of the OpenSea creatures:

```json
{
  "description": "Friendly OpenSea Creature that enjoys long swims in the ocean.", 
  "external_url": "https://openseacreatures.io/3", 
  "image": "https://storage.googleapis.com/opensea-prod.appspot.com/puffs/3.png", 
  "name": "Dave Starbelly",
  "attributes": [ ... ]
}
```

## tokenUri
在 openzeppelin 的框架中，ERC-721 的合约是有 setTokenUri 和 tokenUri 两个函数，用于设定 NFT 制定 tokenId 的 tokenUri。

查看：
https://docs.openzeppelin.com/contracts/2.x/api/token/erc721#ERC721Metadata-_setTokenURI-uint256-string-