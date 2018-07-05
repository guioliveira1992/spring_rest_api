CREATE TABLE `empresa` (
`id` bigint(20) NOT NULL,
`cnpj` varchar(255) NOT NULL,
`data_atualizacao` datetime NOT NULL,
`data_criacao` datetime NOT NULL,
`razao_social` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `funcionario` (
`id` bigint(20) NOT NULL,
`nome` varchar(255) NOT NULL,
`email` varchar(255) NOT NULL,
`cpf` varchar(255) NOT NULL,
`senha` varchar(255) NOT NULL,
`valor_hora` decimal NOT NULL,
`qtde_hora_trabalhada_dia` float DEFAULT NULL,
`qtde_hora_almoco` float DEFAULT NULL,
`perfil` varchar(255) NOT NULL,
`data_atualizacao` datetime NOT NULL,
`data_criacao` datetime NOT NULL,
`empresa_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `lancamento` (
`id` bigint(20) NOT NULL,
`descricao` varchar(255) NOT NULL,
`localizacao` varchar(255) NOT NULL,
`tipo` varchar(255) NOT NULL,
`data_atualizacao` datetime NOT NULL,
`data_criacao` datetime NOT NULL,
`funcionario_fk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `empresa`
ADD PRIMARY KEY (`id`);

ALTER TABLE `empresa`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `funcionario`
ADD PRIMARY KEY (`id`);

ALTER TABLE `funcionario`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `lancamento`
ADD PRIMARY KEY (`id`);

ALTER TABLE `lancamento`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `funcionario` ADD CONSTRAINT `funcionario_empresa_fk` FOREIGN KEY ( `empresa_fk` ) REFERENCES `empresa` ( `id` ) ;
ALTER TABLE `lancamento` ADD CONSTRAINT `lancamento_funcionario_fk` FOREIGN KEY ( `funcionario_fk` ) REFERENCES `funcionario` ( `id` ) ;


